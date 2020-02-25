package at.qe.sepm.skeleton.ui.controllers;

import at.qe.sepm.skeleton.services.PdfCreationService;
import at.qe.sepm.skeleton.ui.beans.InputHandleBean;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.FileNotFoundException;

@Component
@ViewScoped
public class PdfController {

	@Autowired
	PdfCreationService pdfCreationService;

	/**
	 * Creats a PDF-file, which is going to be containing the cryptedText.
	 */
	public void createPDF(InputHandleBean input) {
		try {
			pdfCreationService.createPdf(input.getCryptedText());
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
	}

}
