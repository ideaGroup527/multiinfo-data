package my_servlet;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my_class.Palette;
import my_class.Parameters;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Draw extends HttpServlet {

	public Draw() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int xo = 0, yo = 0;
		int language = Integer.valueOf(request.getParameter("language"));
		String titlecolor = request.getParameter("titlecolor");
		String linescolor = request.getParameter("linescolor");
		String gradcolor = request.getParameter("gradcolor");
		String textcolor = request.getParameter("textcolor");
		int size = Integer.valueOf(request.getParameter("size"));
		int[] titleColor = new int[3];
		int[] linesColor = new int[3];
		int[] gradColor = new int[3];
		int[] textColor = new int[3];
		for (int i = 0; i < 3; i++) {
			titleColor[i] = Integer.valueOf(
					titlecolor.substring(2 * i + 1, 2 * i + 3), 16);
			linesColor[i] = Integer.valueOf(
					linescolor.substring(2 * i + 1, 2 * i + 3), 16);
			gradColor[i] = Integer.valueOf(
					gradcolor.substring(2 * i + 1, 2 * i + 3), 16);
			textColor[i] = Integer.valueOf(
					textcolor.substring(2 * i + 1, 2 * i + 3), 16);
		}
		if (Parameters.calculate.key < 4) {
			for (int i = 1; i < Parameters.x_new.length; i++) {
				if (xo < Parameters.x_new[i][0].getBytes().length)
					xo = Parameters.x_new[i][0].getBytes().length;
			}
		} else {
			for (int i = 1; i < Parameters.x_new[0].length; i++) {
				if (xo < Parameters.x_new[0][i].getBytes().length)
					xo = Parameters.x_new[0][i].getBytes().length;
			}
		}
		xo *= 7.5; // 画水平线的起始横坐标！
		xo += 50;
		yo = size + 20;
		if (xo < 100)
			xo = 100;
		Parameters.xo = xo;
		Parameters.yo = yo;
		OutputStream out = response.getOutputStream();
		BufferedImage image = new BufferedImage(1280 + Parameters.xo, size
				+ (Parameters.calculate.n + 2) * 30, BufferedImage.TYPE_INT_RGB);
		image.getGraphics().setColor(Color.white);
		image.getGraphics().fillRect(0, 0, 1280 + Parameters.xo,
				size + (Parameters.calculate.n + 2) * 30);
		Graphics2D bg = image.createGraphics();
		Graphics2D title = image.createGraphics();
		Graphics2D lines = image.createGraphics();
		Graphics2D grad = image.createGraphics();
		Graphics2D text = image.createGraphics();
		Palette palette = new Palette(Parameters.calculate, Parameters.x_new,
				language);
		palette.paint(bg, title, lines, grad, text, titleColor, linesColor,
				gradColor, textColor, size);
		response.setContentType("image/jpeg");
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
		param.setQuality(1.0f, false);
		encoder.setJPEGEncodeParam(param);
		try {
			encoder.encode(image);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
