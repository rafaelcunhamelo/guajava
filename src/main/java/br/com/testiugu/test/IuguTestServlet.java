package br.com.testiugu.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/checkout/pay")
public class IuguTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap());
        String apiToken = "api_token=4a31c6619673ca4c89d9dae07284cd4d";
        String token = "&token=" + req.getParameter("token");
        String email = "&email=teste@teste.com";
        String paymentMethod = "&payment_method=bank_slip";
        String urlParams = null;
        if (!req.getParameter("token").isEmpty()) {
            System.out.println("Cartão: ");
            urlParams = "https://api.iugu.com/v1/charge?" + apiToken + token + email;
        } else {
            System.out.println("Boleto");
            urlParams = "https://api.iugu.com/v1/charge?" + apiToken + paymentMethod + email;
        }

        String parametrosJSON = "{items: [{description: \"Item 1\",quantity: \"1\",price_cents: \"5990\"},{description: \"Item 2\",quantity: \"1\",price_cents: \"4000\"}]}";
        JSONObject jsonObject = new JSONObject(parametrosJSON);
        System.out.println(jsonObject);

        try {
            URL url = new URL(urlParams);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
                buffer.append(line);
            System.out.println("Response: ");
            System.out.println(buffer.toString());
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        String apiToken = "api_token=4a31c6619673ca4c89d9dae07284cd4d";
        String email = "&email=teste@teste.com";
        String paymentMethod = "&method=bank_slip";
        String urlParams = null;
        System.out.println("Boleto");
        urlParams = "https://api.iugu.com/v1/charge?" + apiToken + paymentMethod + email;
        String parametrosJSON = "{items: [{description: \"Item 1\",quantity: \"1\",price_cents: \"5990\"},{description: \"Item 2\",quantity: \"1\",price_cents: \"4000\"}]}";
        JSONObject jsonObject = new JSONObject(parametrosJSON);
        System.out.println(jsonObject);

        try {
            URL url = new URL(urlParams);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(jsonObject.toString());
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder buffer = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
                buffer.append(line);
            System.out.println("Response: ");
            System.out.println(buffer.toString());
            in.close();
        } catch (Exception e) {
            System.out.println("\nError while calling REST Service");
            System.out.println(e);
        }

    }
}
