package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Province;
import service.Impl.ProvinceServiceImpl;
import service.ProvinceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        调用service查询
        ProvinceService provinceService = new ProvinceServiceImpl();
        List<Province> all = provinceService.findALL();
//        序列化list为json
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(all);
//        调用service查询
        ProvinceService provinceService1 = new ProvinceServiceImpl();
        String allJson = provinceService1.findAllJson();
        System.out.println(allJson);
//        响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(allJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
