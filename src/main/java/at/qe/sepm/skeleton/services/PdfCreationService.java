package at.qe.sepm.skeleton.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfCreationService {
	private Document document;

	/**
	 * Method to create a new PDF-document (A4), with a header and footer, which
	 * will be saved as Verschluesselung.pdf. It will contain a set of tables - each
	 * table one word (assuming encryted)
	 * 
	 * @param text
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void createPdf(String text) throws FileNotFoundException, DocumentException {
		document = new Document(PageSize.A4, 36, 36, 90, 90);
		PdfWriter writer;
		writer = PdfWriter.getInstance(document,
				new FileOutputStream("./src/main/webapp/content/Verschluesselung.pdf"));
		HeaderFooterPage event = new HeaderFooterPage();
		writer.setPageEvent(event);

		document.addAuthor("Benedikt Maximilian Dornauer");
		document.addTitle("Verschlüsselte Nachricht");
		document.open();
		document.add(addWelcomeImage());
		document.add(addWelcomeParapgraph());
		document.add(addDescription());
		addTableForDecryption(this.document, text);

		document.close();
	}

	/**
	 * Create an new paragraph with default font values.
	 * 
	 * @param heading The headline for the paragraph.
	 * @param text    The text for the paragraph.
	 * @return The computed paragraph.
	 */
	public Paragraph addStandardParagraph(String heading, String text) {
		Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(143, 193, 227));
		Paragraph paragraph = new Paragraph(heading);
		paragraph.setFont(normalFont);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		paragraph.add(text);
		return paragraph;
	}

	/**
	 * Adds the logo - welcome image to the pdf
	 * 
	 * @return
	 */
	public Image addWelcomeImage() {
		try {
			Image logo = Image.getInstance("./src/main/webapp/content/key.png");
			logo.setAlignment(Image.MIDDLE);
			logo.scaleAbsolute(100f, 100f);
			return logo;
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Creates an headline for the pdd-document
	 * 
	 * @return
	 */
	public Paragraph addWelcomeParapgraph() {
		Font font = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(143, 193, 227));
		Paragraph paragraph = new Paragraph();
		paragraph.setFont(font);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingAfter(20f);
		paragraph.add("Kryptografie-Anwendung");
		return paragraph;
	}

	/**
	 * Adds a description about the encryption.
	 * 
	 * @return
	 */
	public Paragraph addDescription() {
		Font font = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);
		Paragraph paragraph = new Paragraph();
		paragraph.setFont(font);
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(20f);
		paragraph.add(
				"Mit der Kryptografie-Anwendung wurde eine Nachricht verschlüsselt. Die Geheimbotschaft finden Sie im nachfolgenden Absatz. "
						+ "Falls Sie Fragen zur Verschlüsselung haben, wenden Sie sich an den Ersteller des PDF-Dokuments oder informieren Sie sich auf der Seite: ... ");
		return paragraph;
	}

	/**
	 * Creates a set of tables. Each of them is containing one single word - each
	 * letter one cell. Afterwards the calculated set of tables is added to the
	 * document.
	 * 
	 * @param text
	 * @throws DocumentException
	 */
	public void addTableForDecryption(Document document, String text) throws DocumentException {

		String textArray[] = text.split(" ");
		textArray = splitIfToLong(textArray);
		
		for (int i = 0; i < textArray.length; i++) {
			PdfPTable table = new PdfPTable(textArray[i].length());
			char tableWord[] = textArray[i].toCharArray();

			table.setWidthPercentage((float) textArray[i].length() * 4 + 0.01f);

			for (int j = 0; j < textArray[i].length(); j++) {
				PdfPCell cell = new PdfPCell();
				Paragraph para = new Paragraph(String.valueOf(tableWord[j]));
				cell.addElement(para);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			}
			for (int j = 0; j < textArray[i].length(); j++) {
				PdfPCell cell = new PdfPCell();
				Paragraph para = new Paragraph(" ");
				cell.addElement(para);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				table.addCell(cell);
			}
			table.setSpacingAfter(5f);
			table.setSpacingBefore(5f);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			document.add(table);
		}
	}

	public String[] splitIfToLong(String[] textArray) {
		List<String> newText = new ArrayList<String>();

		for (int i = 0; i < textArray.length; i++) {
			if (textArray[i].length() < 30) {
				newText.add(textArray[i]);
			} else {
				for (int j = 0; j < ((int) (textArray[i].length() / 20) ) ; j++) {
					newText.add(textArray[i].substring(j * 20, (j+1) * 20));
				}
				if(textArray[i].length()%20 != 0) {
					newText.add(textArray[i].substring(textArray[i].length() - (textArray[i].length() % 20) , textArray[i].length()));
				}
			}
		}
		
		return newText.toArray(new String[newText.size()]);
	}

}
