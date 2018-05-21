package com.cj.common.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 服务响应的pojo.
 *
 * @author 崔健
 * @date 2016年9月24日下午9:58:05
 * 完善注释: 20180516
 */
public class TaotaoResult {

    /**
     * 定义jackson对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 响应业务状态.
     * 状态码.
     * 200:成功;  500:错误
     */
    private Integer status;

    /**
     * 响应消息字符串
     */
    private String msg;

    /**
     * 响应中的封装了业务数据的pojo
     */
    private Object data;

    public TaotaoResult() {

    }

    /**
     * 返回由指定的: 状态码,字符串信息,数据对象 组成的TaotaoResult对象
     * 构建者模式
     *
     * @param status 状态码
     * @param msg    字符串消息
     * @param data   封装了业务数据的pojo
     * @return TaotaoResult
     * @author cj
     */
    public static TaotaoResult build(Integer status, String msg, Object data) {
        return new TaotaoResult(status, msg, data);
    }

    /**
     * 返回由指定的: 状态码,字符串信息,数据对象 组成的TaotaoResult对象
     * 构建者模式
     * data默认为null.
     * 通常在异常处理的catch中,调用本方法.
     * 例如:
     * return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
     *
     * @param status 状态码
     * @param msg    字符串消息
     * @return TaotaoResult
     * @author cj
     */
    public static TaotaoResult build(Integer status, String msg) {
        return new TaotaoResult(status, msg, null);
    }

    /**
     * 返回由指定的: 数据对象 组成的TaotaoResult对象
     * 通常在正常返回数据时,调用本方法.
     * 例如:
     * return TaotaoResult.ok(item);
     *
     * @param data 封装了业务数据的pojo
     * @return TaotaoResult
     * @author cj
     */
    public static TaotaoResult ok(Object data) {
        return new TaotaoResult(data);
    }

    //

    /**
     * 静态方法:ok
     * 内部new一个本类对象,传一个空,即会调用本类的构造方法.
     * 通常在客户端不需要data数据时,调用本方法.
     *
     * @return 不含data的TaotaoResult.(但含有默认的status 和 msg)
     * @author cj
     */
    public static TaotaoResult ok() {
        return new TaotaoResult(null);
    }

    public TaotaoResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TaotaoResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz    TaotaoResult中的object类型
     * @return TaotaoResult
     * @author cj
     */
    public static TaotaoResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, TaotaoResult.class);
            }

            //拿到json中的data部分.此部分含有业务数据.
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isObject()) {
                obj = MAPPER.readValue(data.traverse(), clazz);
            } else if (data.isTextual()) {
                obj = MAPPER.readValue(data.asText(), clazz);
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * 没有指定object对象的转化
     *
     * @param json json数据
     * @return TaotaoResult
     * @author cj
     */
    public static TaotaoResult format(String json) {
        try {
            return MAPPER.readValue(json, TaotaoResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return TaotaoResult
     * @author cj
     */
    public static TaotaoResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}	