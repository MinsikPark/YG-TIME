<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%=(JSONObject)request.getAttribute("json")%>