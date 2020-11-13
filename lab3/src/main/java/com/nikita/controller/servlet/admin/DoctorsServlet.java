package com.nikita.controller.servlet.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.nikita.controller.command.admin.GetDoctorsCommand;
import com.nikita.model.entity.Doctor;
import com.nikita.util.ArrayUtil;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class DoctorsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String[] doctorsCategoriesParams = req.getParameterValues("doctorCategory");
        int[] doctorsCategories = doctorsCategoriesParams != null
            ? ArrayUtil.getIntArray(
                Arrays.stream(req.getParameterValues("doctorCategory"))
                .map(doctorCategory -> Integer.parseInt(doctorCategory))
                .toArray(Integer[]::new)
            )
            : null;
        GetDoctorsCommand getDoctorsCommand = new GetDoctorsCommand(doctorsCategories);
        List<Doctor> doctors = getDoctorsCommand.execute();

        resp.setContentType("text/yaml; charset=UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            objectMapper.writeValue(writer, doctors);
        } catch (IOException err) {
            System.err.println(err);
            resp.setStatus(500);
        }
    }
}