package at.qe.sepm.skeleton.ui.beans;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@Scope("session")
public class ChartBean {

	private Double frequenzLetterGermanAlphabet[] = new Double[] { 6.51d, 1.89d, 3.06d, 5.08d, 17.40d, 1.66d, 3.01d,
			4.76d, 7.55d, 0.27d, 1.21d, 3.44d, 2.53d, 9.78d, 2.51d, 0.79d, 0.02d, 7.00d, 7.27d, 6.15d, 4.35d, 0.67d,
			1.89d, 0.03d, 0.04d, 1.13d };

	private String[] lateinAlphabet = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	
	private Double frequenzGermanBigramm[] = new Double[] { 3.8d, 3.7d, 2.6d, 2.1d, 2.0d, 1.9d, 
																   1.9d, 1.7d, 1.7d, 1.4d, 1.2d,  1.2d, 
																   1.2d,  1.2d, 1.1d, 1.0d, 1.0d, 1.0d}; 
	private String[] bigramme = {"en", "er", "ch", "de", "ei","nd", 
								 "te", "in", "ie", "ge", "ne" , "es", 
								 "un","st", "re", "he", "be", "an"};
	
	private Integer[] countInputLetters = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	private BarChartModel germanAlphabetChart;
	private BarChartModel bigrammChart; 
	private BarChartModel countInputLettersChart;

	@PostConstruct
	public void init() {
		createChartGermanAlphabet();
		createBigrammChart();
		updateCountInputLettersChart();
	}

	public void createChartGermanAlphabet() {
		this.germanAlphabetChart = createCharAlphabet(lateinAlphabet,frequenzLetterGermanAlphabet,"Häufigkeitsdiagramm Deutsches Alphabet","Buchstabe","Relative Häufigkeiten in Prozent");
    }
	
	public void createBigrammChart() {
		this.bigrammChart = createCharAlphabet(bigramme,frequenzGermanBigramm,"Häufigkeiten von deutschen Bigrammen","Buchstabe","Relative Häufigkeit in Prozent");
    }

	public void updateCountInputLettersChart() {
		countInputLettersChart = createCharAlphabet(lateinAlphabet, countInputLetters,"Absolute Häufigkeiten Input","Buchstabe","Absolute Häufigkeit");
	}
	
	public BarChartModel createCharAlphabet(String[] alphabet, Number[] frequenzAlphabet, String title, String xTitle,
			String yTitle) {
		BarChartModel model = new BarChartModel();
		ChartSeries chartData = new ChartSeries();

		for (int i = 0; i < alphabet.length; i++) {
			chartData.set(alphabet[i], frequenzAlphabet[i]);
		}
		
		model.addSeries(chartData);
		model.setTitle(title);

		Axis xAxis = model.getAxis(AxisType.X);
		xAxis.setLabel(xTitle);

		Axis yAxis = model.getAxis(AxisType.Y);
		yAxis.setLabel(yTitle);
		return model;
	}

	public BarChartModel getGermanAlphabetChart() {
		return germanAlphabetChart;
	}

	public void setGermanAlphabetChart(BarChartModel germanAlphabetChart) {
		this.germanAlphabetChart = germanAlphabetChart;
	}

	public String[] getBigramme() {
		return bigramme;
	}

	public void setBigramme(String[] bigramme) {
		this.bigramme = bigramme;
	}

	public Integer[] getCountInputLetters() {
		return countInputLetters;
	}

	public void setCountInputLetters(Integer[] countInputLetters) {
		this.countInputLetters = countInputLetters;
	}

	public BarChartModel getBigrammChart() {
		return bigrammChart;
	}

	public void setBigrammChart(BarChartModel bigrammChart) {
		this.bigrammChart = bigrammChart;
	}

	public BarChartModel getCountInputLettersChart() {
		return countInputLettersChart;
	}

	public void setCountInputLettersChart(BarChartModel countInputLettersChart) {
		this.countInputLettersChart = countInputLettersChart;
	}
}
