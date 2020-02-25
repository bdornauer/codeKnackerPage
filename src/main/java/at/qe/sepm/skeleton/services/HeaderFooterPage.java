package at.qe.sepm.skeleton.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderFooterPage extends PdfPageEventHelper {

	/**
	 * Adding a header to the pdf-document, on each side.
	 */
	public void onStartPage(PdfWriter writer, Document document) {
		String pattern = "dd.MM.yyyy-HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Kryotgrafie-Anwendung"),
				40, 800, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase(date), 550, 800, 0);
	}

	/**
	 * Adding a footer to the pdf-document, on each side.
	 */
	public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("Link: "), 40, 30, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,
				new Phrase("Seite" + document.getPageNumber()), 550, 30, 0);
	}

}