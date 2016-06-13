/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.eastpro.web.controller;

import com.eastpro.web.form.Rider;
import com.eastpro.web.form.RiderCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JsonController {
    private static final Logger logger = LoggerFactory.getLogger(JsonController.class);

    @RequestMapping(value = "/getRider.eab", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRiderByPlanCode(@RequestParam("planCode")String planCode) {
        logger.info("---------planCode["+planCode+"]--------");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        ArrayList<RiderCollection> rcList = new ArrayList<RiderCollection>();
        RiderCollection rc;
        Rider r;
        ArrayList<Rider> riderList;

        rc = new RiderCollection();
        rc.setRiderType("DI");
        rc.setRiderTypeDesc("DISABILITY INCOME");
        riderList = new ArrayList<Rider>();
        r = new Rider();
        r.setRiderCode("1");
        r.setRiderName("Accelerated Disability");
        riderList.add(r);
        rc.setRiderList(riderList);
        rcList.add(rc);

        rc = new RiderCollection();
        rc.setRiderType("ME");
        rc.setRiderTypeDesc("Medical");
        riderList = new ArrayList<Rider>();
        r = new Rider();
        r.setRiderCode("2");
        r.setRiderName("Medical Cash");
        riderList.add(r);
        rc.setRiderList(riderList);
        rcList.add(rc);

        rc = new RiderCollection();
        rc.setRiderType("TE");
        rc.setRiderTypeDesc("TERM");
        riderList = new ArrayList<Rider>();
        r = new Rider();
        r.setRiderCode("3");
        r.setRiderName("Term Life Rider");
        riderList.add(r);
        rc.setRiderList(riderList);
        rcList.add(rc);

        modelMap.put("suppPlans", rcList);
        return modelMap;
    }

    @RequestMapping(value = "/ppscalculate.eab", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> calculate(@RequestParam("parameter")String parameter) {
        logger.info("---------parameter["+parameter+"]--------");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("suppPlans", "11111111111111111");
        return modelMap;
    }
}
