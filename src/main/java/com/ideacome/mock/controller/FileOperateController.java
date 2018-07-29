package com.ideacome.mock.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ideacome.mock.returnDTO.ResultMsg;

@RestController
@RequestMapping("/file")
public class FileOperateController {

	@RequestMapping("/upload")
	public ResultMsg upload(HttpServletRequest request) {
		// 先实例化一个文件解析器
		CommonsMultipartResolver coMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String str = "{";
		// 判断request请求中是否有文件上传
		if (coMultipartResolver.isMultipart(request)) {
			try {
				Iterator<Part> iterator = request.getParts().iterator();
				while (iterator.hasNext()) {// 遍历客户端上传的所有文件
					Part part = (Part) iterator.next();
					if (part.getSize() == 0) {// 不执行大小为0的属性值
						continue;
					}
					System.out.println("part.getSize()：" + part.getSize());// 获取上传文件的大小
					System.out.println("part.getName()：" + part.getName());// 获取上传文件的名及添加数据时的key名
					if(part.getName().contains(".")) {
						File file = new File(this.getClass().getResource("/").getPath() + part.getName());
						InputStream inputStream = part.getInputStream();
						FileOutputStream fos = new FileOutputStream(file);
						byte[] buffer = new byte[1024];
						int len = 0;
						while ((len = inputStream.read(buffer)) != -1) {
							fos.write(buffer, 0, len);
						}
						inputStream.close();
						fos.close();
					}else {
						BufferedReader br = new BufferedReader(new InputStreamReader(part.getInputStream()));
						StringBuffer sb = new StringBuffer();
						sb.append(part.getName()+":");
						String next = "";  
			            while((next=br.readLine())!=null){  
			                sb.append(next);
			            }
			            sb.append(",");
						str = sb.toString();
					}
				}
				
			} catch (IOException | ServletException e) {
				e.printStackTrace();
				return ResultMsg.newInstance(500, "上传文件失败：" + e.getMessage());
			}
		}
		str += "}";

		return ResultMsg.newInstance(10000, StringUtils.isEmpty(str)?"上传文件成功":str);
	}
}
