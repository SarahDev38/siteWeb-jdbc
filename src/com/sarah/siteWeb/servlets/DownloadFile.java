package com.sarah.siteWeb.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/fichiers/*", initParams = {
		@WebInitParam(name = "chemin", value = "C:/Users/SILVESTRE.PC-SARAH/Desktop/informatique/imagesTP1/fichiers/") })
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int TAILLE_TAMPON = 10240; // 10 ko

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chemin = this.getServletConfig().getInitParameter("chemin");
		String fichierRequis = request.getPathInfo();
		if (fichierRequis == null || "/".equals(fichierRequis)) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		fichierRequis = URLDecoder.decode(fichierRequis, "UTF-8");
		File fichier = new File(chemin, fichierRequis);
		if (!fichier.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		/* Récupère le type du fichier */
		String type = getServletContext().getMimeType(fichier.getName());
		if (type == null) {
			type = "application/octet-stream";
		}
		response.reset();
		response.setBufferSize(TAILLE_TAMPON);
		response.setContentType(type);
		response.setHeader("Content-Length", String.valueOf(fichier.length()));
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fichier.getName() + "\"");
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(new FileInputStream(fichier), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(response.getOutputStream(), TAILLE_TAMPON);
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

}
