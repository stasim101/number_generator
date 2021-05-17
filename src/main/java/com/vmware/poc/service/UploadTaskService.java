package com.vmware.poc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmware.poc.enums.UploadTaskStatus;
import com.vmware.poc.model.UploadTask;
import com.vmware.poc.repository.UploadTaskRepository;

@Service
public class UploadTaskService {

	@Autowired
	private UploadTaskRepository uploadTaskRepository;

	public UploadTask generateTask(String fileName) {

		return uploadTaskRepository.save(new UploadTask(UploadTaskStatus.PROCESSING, fileName));
	}

	public UploadTaskStatus getTaskStatusById(long taskId) {

		UploadTask uploadTask = getTaskById(taskId);

		if (uploadTask == null)
			return UploadTaskStatus.UNAVAILABLE;

		return uploadTask.getTaskStatus();
	}

	public UploadTaskStatus setTaskStatusById(long taskId, UploadTaskStatus uploadTaskStatus) {

		UploadTask uploadTask = getTaskById(taskId);

		if (uploadTask == null)
			return UploadTaskStatus.UNAVAILABLE;

		uploadTask.setTaskStatus(uploadTaskStatus);
		uploadTaskRepository.save(uploadTask);
		return getTaskStatusById(taskId);
	}

	public UploadTask getTaskById(long taskId) {

		Optional<UploadTask> uploadTaskOptional = uploadTaskRepository.findById(taskId);

		if (uploadTaskOptional.isPresent())
			return uploadTaskOptional.get();

		return null;
	}
}
