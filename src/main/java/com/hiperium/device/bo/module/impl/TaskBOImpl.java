/**
 * Product  : Hiperium Project
 * Architect: Andres Solorzano.
 * Created  : 08-05-2009 - 23:30:00
 * 
 * The contents of this file are copyrighted by Andres Solorzano 
 * and it is protected by the license: "GPL V3." You can find a copy of this 
 * license at: http://www.hiperium.com/about/licence.html
 * 
 * Copyright 2014 Andres Solorzano. All rights reserved.
 * 
 */
package com.hiperium.device.bo.module.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.services.EnumDeviceAction;
import com.hiperium.commons.services.bean.BeanUtils;
import com.hiperium.commons.services.exception.EnumInformationException;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.device.bo.generic.GenericBO;
import com.hiperium.device.bo.module.DeviceBO;
import com.hiperium.device.bo.module.TaskBO;
import com.hiperium.device.dao.module.DeviceTaskDAO;
import com.hiperium.device.model.EnumFrequency;
import com.hiperium.device.model.control.DeviceTask;
import com.hiperium.device.model.control.Task;
import com.hiperium.device.model.control.TaskDTO;

/**
 * This service is the implementation of the interface TaskLocal
 * and manages all actions needed for the task management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
public class TaskBOImpl extends GenericBO implements TaskBO {
	
	/** The property log. */
	@Inject
	private HiperiumLogger log;
	
	/** The property deviceBO. */
	@EJB
	private DeviceBO deviceBO;
	
	/** The property deviceTaskDAO. */
	@EJB
	private DeviceTaskDAO deviceTaskDAO;
	
	/** EJB Timer Service Resource. */
	@Resource
	private TimerService timerService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(@NotNull TaskDTO task) throws InformationException {
		this.log.debug("create - START");
		if (task.getExecutionDate().after(new Date())) {
			Task register = super.getDaoFactory().getTaskDAO().create(task);
			if(EnumFrequency.ONCE.equals(register.getFrequency())) {
				this.createSingleTimer(register);
			} else {
				this.createIntervalTimer(register);
			}
		} else {
			throw new InformationException(EnumInformationException.TASK_DATE_TIME.getCode());
		}
		this.log.debug("create - END");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(@NotNull TaskDTO task) throws InformationException {
		this.log.debug("update - START");
		Task register = super.getDaoFactory().getTaskDAO().update(task);
		// Find the actual timer and deletes it.
		Timer t = this.findTimer(task.getId());
		if(t != null) {
			t.cancel();
			this.log.debug("delete: \"" + t + "\" canceled.");
		}
		// Now creates the updated timer task.
		if(EnumFrequency.ONCE.equals(register.getFrequency())) {
			this.createSingleTimer(register);
		} else {
			this.createIntervalTimer(register);
		}
		this.log.debug("update - END");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(@NotNull @Min(value = 1L) Long id) throws InformationException {
		this.log.debug("delete - START"); 
		super.getDaoFactory().getTaskDAO().delete(id);
		Timer t = this.findTimer(id);
		if(t != null) {
			t.cancel();
			this.log.debug("delete: \"" + t + "\" canceled.");
		}
    	this.log.debug("delete - END"); 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Task findById(@NotNull @Min(value = 1L) Long id, boolean lock) throws InformationException {
		return super.getDaoFactory().getTaskDAO().findById(id, lock, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TaskDTO> findByHomeId(@NotNull @Min(value = 1L) Long homeId) throws InformationException {
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
		List<Task> list = super.getDaoFactory().getTaskDAO().findByHomeId(homeId);
		for(Task task : list) {
			TaskDTO dto = new TaskDTO();
			taskDTOs.add(dto);
			try {
				BeanUtils.copyProperties(task, dto);
			} catch (Exception e) {
				this.log.error(e.getMessage(), e);
			}
		}
		return taskDTOs;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Timeout
	public void excecute(Timer timer) {
		Long taskId = (Long) timer.getInfo();
		this.log.debug("excecute - Task ID: " + taskId);
		try {
			Task task = super.getDaoFactory().getTaskDAO().findById(taskId, false, true);
			if(task != null && task.getActive()){
				List<DeviceTask> deviceList = super.getDaoFactory().getDeviceTaskDAO().findByTaskId(task.getId());
				for(DeviceTask device : deviceList) {
					DeviceDTO dto = super.getDaoFactory().getDeviceDAO().findById(device.getPk().getDeviceId());
					if(task.getAction().equals(EnumDeviceAction.ACTIVATE) || task.getAction().equals(EnumDeviceAction.LOCK)){
						dto.setStatus(true);
					} else if(task.getAction().equals(EnumDeviceAction.DEACTIVATE) || task.getAction().equals(EnumDeviceAction.UNLOCK)){
						dto.setStatus(false);
					}
					// TODO: must get the microservice token ID
					this.deviceBO.userOperation(dto, "");
				}
				
				//CREATE A NEW TIMER SERVICE FOR FREQUENCY MORE THAN DAILY
				if(EnumFrequency.WEEKLY.equals(task.getFrequency())
						|| EnumFrequency.MONTHLY.equals(task.getFrequency())
						|| EnumFrequency.QUARTERLY.equals(task.getFrequency())
						|| EnumFrequency.SEMIANNUAL.equals(task.getFrequency())
						|| EnumFrequency.ANNUAL.equals(task.getFrequency())) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					switch (task.getFrequency()) {
					case WEEKLY:
						this.log.debug("excecuteTask - WEEKLY");
						calendar.add(Calendar.DAY_OF_MONTH, 7);
						break;
					case MONTHLY:
						this.log.debug("excecuteTask - MONTHLY");
						calendar.add(Calendar.MONTH, 1);
						break;
					case QUARTERLY:
						this.log.debug("excecuteTask - QUARTERLY");
						calendar.add(Calendar.MONTH, 3);
						break;
					case SEMIANNUAL:
						this.log.debug("excecuteTask - SEMIANNUAL");
						calendar.add(Calendar.MONTH, 6);
						break;	
					case ANNUAL:
						this.log.debug("excecuteTask - ANNUAL");
						calendar.add(Calendar.YEAR, 1);
						break;	
					default:
						break;
					}
					// We recreate the task with the new manipulated date time.
					this.timerService.createSingleActionTimer(calendar.getTime(), 
							new TimerConfig(task.getId(), true));
				}
			}
		} catch (Exception e) {
			this.log.error("Error: " + e.getMessage());
		}
		timer.cancel(); 
		this.log.debug("excecute - END");
	}
	
	/**
	 * 
	 * @param task
	 */
	private void createSingleTimer(Task task) {
		this.log.debug("createSingleTimer - START");
		this.timerService.createSingleActionTimer(task.getExecutionDate(), new TimerConfig(task.getId(), true));
		this.log.debug("createSingleTimer - END");
	}
	
	/**
	 * 
	 * @param task
	 */
	private void createIntervalTimer(Task task) {
		this.log.debug("createIntervalTimer - START: " + task.getExecutionDate());
		Long interval = 0L;
		switch (task.getFrequency()) {
			case EVERY_MIMUTE:
				this.log.debug("createIntervalTimer - EVERY_MIMUTE");
				interval = 60000L;
				break;	
			case QUARTER_HOUR:
				this.log.debug("createIntervalTimer - QUARTER_HOUR");
				interval = 900000L;
				break;	
			case HALF_HOUR:
				this.log.debug("createIntervalTimer - HALF_HOUR");
				interval = 1800000L;
				break;
			case HOURLY:
				this.log.debug("createIntervalTimer - HOURLY");
				interval = 3600000L;
				break;	
			case DAILY:
				this.log.debug("createIntervalTimer - DAILY");
				interval = 86400000L;
				break;
			default:
				break;
		}
		this.log.debug("Interval in milliseconds: " + interval);
		this.timerService.createIntervalTimer(task.getExecutionDate(), interval, new TimerConfig(task.getId(), true));
		this.log.debug("createIntervalTimer - END");
	}
	
	/**
	 * 
	 * @param taskId
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private Timer findTimer(Long taskId) {
		this.log.debug("findTimer - START");
		Timer timer = null;
		Collection<Timer> timers = this.timerService.getTimers();
		if (timers != null) {
			for (Iterator<Timer> iterator = timers.iterator(); iterator.hasNext();) {
				Timer t = iterator.next();
				try {
					if (taskId.equals((Long) t.getInfo())) {
						timer = t;
					}
				} catch (Exception e) {
					this.log.debug("ERROR: " + e.getMessage());
				}
			}
		}
		this.log.debug("findTimer - END");
		return timer;
	}

	
}
