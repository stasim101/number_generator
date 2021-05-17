package com.vmware.poc.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.vmware.poc.enums.UploadTaskStatus;

@Entity
public class UploadTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long taskId;
	@Enumerated(EnumType.ORDINAL)
	private UploadTaskStatus status;
	private String fileName;

	public UploadTask() {
	}

	public UploadTask(UploadTaskStatus status, String fileName) {
		this.status = status;
		this.fileName = fileName;
	}

	public long getTaskid() {
		return taskId;
	}

	public void setTaskid(long taskId) {
		this.taskId = taskId;
	}

	public UploadTaskStatus getTaskStatus() {
		return status;
	}

	public void setTaskStatus(UploadTaskStatus status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
