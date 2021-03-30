package com.emergentes.controller;

import com.emergentes.modelo.Nota;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProcesaServlet", urlPatterns = {"/ProcesaServlet"})
public class ProcesaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("op");
        Nota not = new Nota();
        int id, pos;
        HttpSession ses = request.getSession();
        List<Nota> lista = (List<Nota>) ses.getAttribute("listaage");
        switch (opcion) {
            case "nuevo":
                request.setAttribute("minota", not);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPosicion(request, id);
                not = lista.get(pos);
                request.setAttribute("minota", not);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarPosicion(request, id);
                if (pos >= 0) {
                    lista.remove(pos);
                }
                request.setAttribute("listaage", lista);
                response.sendRedirect("index.jsp");
                break;
            default:
                request.setAttribute("listaage", lista);
                response.sendRedirect("index.jsp");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Nota> lista = (ArrayList<Nota>) ses.getAttribute("listaage");
        Nota nota = new Nota();
        nota.setId(id);
        nota.setHora(request.getParameter("hora"));
        nota.setActividad(request.getParameter("actividad"));
        nota.setCompletado(request.getParameter("completado"));
        System.out.println("El ID es " + id);
        if (id == 0) {
            int idNuevo = ObtenerId(request);
            nota.setId(idNuevo);
            
            lista.add(nota);
            System.out.println(nota.toString());
        } else {
            int pos = buscarPosicion(request, id);
            lista.set(pos, nota);
            System.out.println(nota.toString());
        }
        System.out.println("Enviando as index");
        request.setAttribute("listaage", lista);
        response.sendRedirect("index.jsp");

    }

    private int buscarPosicion(HttpServletRequest request, int id) {
        HttpSession ses = request.getSession();
        List<Nota> lista = (List<Nota>) ses.getAttribute("listaage");

        int pos = -1;
        if (lista != null) {
            for (Nota not : lista) {
                ++pos;
                if (not.getId() == id) {
                    break;
                }
            }
        }
        return pos;
    }

    private int ObtenerId(HttpServletRequest request) {
        HttpSession ses = request.getSession();
        ArrayList<Nota> lista = (ArrayList<Nota>) ses.getAttribute("listaage");
        int idn = 0;
        for (Nota nota : lista) {
            idn = nota.getId();
        }
        return idn + 1;
    }

}
