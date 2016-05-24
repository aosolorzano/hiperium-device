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
package com.hiperium.device.dao.module.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.services.EnumDeviceAction;
import com.hiperium.device.dao.EnumNamedQueryControl;
import com.hiperium.device.dao.GenericDAO;
import com.hiperium.device.dao.module.DeviceTaskDAO;
import com.hiperium.device.dao.module.TaskDAO;
import com.hiperium.device.model.EnumFrequency;
import com.hiperium.device.model.control.DeviceTask;
import com.hiperium.device.model.control.Task;
import com.hiperium.device.model.control.TaskDTO;

/**
 * This service is the implementation of the interface TaskLocal and manages all
 * actions needed for the task management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TaskDAOImpl extends GenericDAO<Task, Long> implements TaskDAO {
	
	/** The property entityManger. */
	@Inject
    private EntityManager entityManager;
	
	/** The property deviceTaskDAO. */
	@EJB
	private DeviceTaskDAO deviceTaskDAO;
	
	/**
	 * Component post contruct.
	 */
	@PostConstruct
	public void init() {
		super.setEntityManager(this.entityManager);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Task create(TaskDTO taskDTO) throws InformationException {
		// Persist the task
		Task task = new Task();
		task.setId(this.getNextSequential());
		task.setActive(true);
		task.setAction(EnumDeviceAction.decodeValue(taskDTO.getAction()));
		task.setComments(taskDTO.getComments());
		task.setDeviceOperationLevel(task.getDeviceOperationLevel());
		task.setExecutionDate(taskDTO.getExecutionDate());
		task.setFrequency(EnumFrequency.decodeValue(taskDTO.getFrequency()));
		task.setHomeId(taskDTO.getHomeId());
		
		// Assign devices associated to this task
		for(DeviceDTO dto : taskDTO.getSelectedDevices()) {
			DeviceTask deviceTask = new DeviceTask(dto.getId(), task.getId());
			deviceTask.setActive(true);
			deviceTask.setTask(task);
			task.getDevices().add(deviceTask);
		}
		super.create(task);
		return task;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Task update(TaskDTO taskDTO) throws InformationException {
		// TODO: IMPLEMENT
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) throws InformationException {
		Task task = super.findById(id, false, false);
		List<DeviceTask> deviceList = this.deviceTaskDAO.findByTaskId(task.getId());
		for(DeviceTask device : deviceList) {
			this.entityManager.remove(device);
		}
		this.entityManager.remove(task);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Task> findByHomeId(@NotNull @Min(value = 1L) Long homeId) throws InformationException {
		return super.createNamedQuery(EnumNamedQueryControl.TASK_FIND_BY_HOME_ID.getName(), true)
				.setParameter("homeId", homeId)
				.getResultList();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Task findById(Long id, boolean lock, boolean bypassCache) {
		return super.findById(id, lock, bypassCache);
	}
	
	/**
	 * Find the next sequential for the register.
	 * 
	 * @return the next sequential for the register.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	private Long getNextSequential() {
		Query query = super.getEntityManager().createNamedQuery(EnumNamedQueryControl.TASK_NEXT_SEQUENTIAL.getName());
		return query.getSingleResult() == null ? 1L : ((Long) query.getSingleResult()) + 1L;
	}
}
