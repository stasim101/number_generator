package com.vmware.poc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vmware.poc.model.UploadTask;

@Repository
public interface UploadTaskRepository extends CrudRepository<UploadTask, Long> {

}
