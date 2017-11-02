package com.ss.SocialistB.dao;

import com.ss.SocialistB.model.*;

public interface UploadFileDao {

	 boolean save(UploadFile uploadFile);

	UploadFile getFile(String userName);
}
