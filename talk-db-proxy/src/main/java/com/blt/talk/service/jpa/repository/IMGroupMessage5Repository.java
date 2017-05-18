/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.blt.talk.service.jpa.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.blt.talk.service.jpa.entity.IMGroupMessage5;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public interface IMGroupMessage5Repository extends PagingAndSortingRepository<IMGroupMessage5, Long>, JpaSpecificationExecutor<IMGroupMessage5> {

}