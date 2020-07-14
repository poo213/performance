package com.njmetro.performance.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njmetro.performance.domain.Employee;
import com.njmetro.performance.exception.EmployeeException;
import com.njmetro.performance.exception.EmployeeNotFoundException;
import com.njmetro.performance.exception.ErrorEnum;
import com.njmetro.performance.service.EmployeeService;
import com.njmetro.performance.util.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 职工 Service 实现
 *
 * @author RCNJTECH
 * @date 2020/4/14 14:06
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final String APPID = "2020071316161269";
    private static final String APPWD = "eab9269fa9c64eeda45e54b894ffc61a";

    /**
     * 根据免登授权码查询职工信息
     *
     * @param code 免登授权码
     * @return 职工信息
     */
    @Override
    public Employee getByCode(String code) {
        long timeStamp = System.currentTimeMillis() / 1000;
        String signature = DigestUtils.sha1Hex(APPID + APPWD + timeStamp).toUpperCase();
        String url = "http://192.168.138.122:8011/MicroApp/ws_microapp.asmx/GetUserDataV3?appid=" + APPID
                + "&timeStamp=" + timeStamp + "&code=" + code + "&signature=" + signature;
        return getEmployee(url);
    }
    /**
     * 从服务器查询职工信息
     *
     * @param url URL
     * @return 职工信息
     */
    private Employee getEmployee(String url) {
        log.info("urlgetEmployee :{}",url);
        String result = HttpUtils.getEncoded(url);
        log.info("result :{}",result);
        if (result == null) {
            throw new EmployeeException(HttpStatus.NOT_FOUND, ErrorEnum.EMPLOYEE_NOT_FOUND);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode resultNode = objectMapper.readValue(result, JsonNode.class);
            if (resultNode.get("errcode").asInt() == 0) {
                JsonNode data = resultNode.get("data");
                String userid = data.get("userid").asText();
                String name = data.get("name").asText();
                return new Employee(userid,name,"","","","","0");
            }
            else{
                log.error("服务器的职工信息（新接口）：查询失败");
                throw new EmployeeNotFoundException();
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new EmployeeException(HttpStatus.NOT_FOUND, ErrorEnum.EMPLOYEE_NOT_FOUND);
        }
    }

}
