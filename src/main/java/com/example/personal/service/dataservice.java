package com.example.personal.service;


import com.example.personal.domain.info;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dataservice {
    private int nowId = -2;
    @Resource
    private MongoTemplate mongoTemplate;

    public info getLast(){
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("_id"))).limit(1);
        List<info> InfoList = mongoTemplate.find(query, info.class, "info");
        if (InfoList.isEmpty()){
            return null;
        }
        return InfoList.get(0);
    }

    private void set_now_id() {
        if (this.nowId == -2){
            info last = getLast();
            if (last == null){
                nowId = 0;
            }else {
                nowId = last.getId();
            }
        }
    }

    public void saveAll(info info) {
        set_now_id();
        info.setId(++nowId);
        mongoTemplate.save(info);
    }
}
