/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.blt.talk.service.internal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blt.talk.common.util.CommonUtils;
import com.blt.talk.service.internal.RelationShipService;
import com.blt.talk.service.jpa.entity.IMRelationShip;
import com.blt.talk.service.jpa.repository.IMRelationShipRepository;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Service
public class RelationShipServiceImpl implements RelationShipService{

    @Autowired
    private IMRelationShipRepository relationShipRepository;
    
    /* (non-Javadoc)
     * @see com.blt.talk.service.service.RelationShipService#getRelationId(int, int)
     */
    @Override
    public Long getRelationId(long userId, long toId, boolean add) {
        
        Long relateId = 0L;
        
        long smallId = Math.min(userId, toId);
        long bigId = Math.max(userId, toId);
        byte status = 0;
        
        List<IMRelationShip> relationShipList = relationShipRepository.findBySmallIdAndBigIdAndStatus(smallId, bigId, status);
        
        if (!relationShipList.isEmpty()) {
            relateId = relationShipList.get(0).getId();
        } else {
            
            if (add) {
                byte statusDel = 1;
                relationShipList = relationShipRepository.findBySmallIdAndBigIdAndStatus(smallId, bigId, statusDel);
                IMRelationShip relationShip;
                if (!relationShipList.isEmpty()) {
                    relationShip = relationShipList.get(0);
                    relationShip.setStatus(status);
                    relateId = relationShipRepository.save(relationShip).getId();
                } else {
                      int time = CommonUtils.currentTimeSeconds();
                        
                      relationShip = new IMRelationShip();
                      relationShip.setSmallId(smallId);
                      relationShip.setBigId(bigId);
                      relationShip.setStatus(status);
                      relationShip.setUpdated(time);
                      relationShip.setCreated(time);
                      relateId = relationShipRepository.save(relationShip).getId();
                }
            }
        }
        
        return relateId;
    }

}
