import comparing.Extrema;
import crossing.Crossover;
import mutation.Mutation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import selection.Selection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GeneticAlgorithmForm {
    private JButton acceptButton;
    private JPanel panelMain;
    private JLabel populationLabel;
    private JLabel epochsLabel;
    private JLabel extremaLabel;
    private JLabel selectionLabel;
    private JComboBox extremaComboBox;
    private JComboBox selectionComboBox;
    private JSpinner populationSpinner;
    private JSpinner epochsSpinner;
    private JSpinner selectionProbSpinner;
    private JLabel selectionProbLabel;
    private JComboBox crossoverComboBox;
    private JSpinner crossoverProbSpinner;
    private JLabel crossoverLabel;
    private JLabel crossoverProbLabel;
    private JComboBox mutationComboBox;
    private JSpinner mutationProbSpinner;
    private JLabel mutationLabel;
    private JLabel mutationProbLabel;
    private JSpinner inversionProbSpinner;
    private JLabel inversionLabel;
    private JLabel eliteStrategyLabel;
    private JComboBox eliteStrategyComboBox;
    private JTextArea resultTextArea;
    private JPanel plotPanel;
    private JButton plot1Button;
    private JButton plot2Button;
    private JButton plot3Button;
    private JSpinner eliteStrategyBestAmountSpinner;
    private JLabel eliteStrategyBestAmountLabel;
    GeneticAlgorithm geneticAlgorithm;

    public GeneticAlgorithmForm() {
        populationSpinner.setModel(new SpinnerNumberModel(30, 0, 1000, 1));
        epochsSpinner.setModel(new SpinnerNumberModel(500f, 0f, 1000f, 1f));
        selectionProbSpinner.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        crossoverProbSpinner.setModel(new SpinnerNumberModel(90, 0, 100, 1));
        mutationProbSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        inversionProbSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        eliteStrategyBestAmountSpinner.setModel(new SpinnerNumberModel(1, 0, 5, 1));
        resultTextArea.setSize(100, 1000);
        acceptButton.addActionListener(e -> {
            geneticAlgorithm = new GeneticAlgorithm((Integer) populationSpinner.getValue(), Float.parseFloat(String.valueOf(epochsSpinner.getValue())),
                    Extrema.valueOf(extremaComboBox.getSelectedIndex()),
                    Selection.valueOf(selectionComboBox.getSelectedIndex()), (Integer) selectionProbSpinner.getValue(),
                    Crossover.valueOf(crossoverComboBox.getSelectedIndex()), (Integer) crossoverProbSpinner.getValue(),
                    Mutation.valueOf(mutationComboBox.getSelectedIndex()), (Integer) mutationProbSpinner.getValue(),
                    (Integer) inversionProbSpinner.getValue(),
                    eliteStrategyComboBox.getSelectedIndex(), (Integer) eliteStrategyBestAmountSpinner.getValue());
            geneticAlgorithm.setResultTextArea(resultTextArea);
            geneticAlgorithm.run();
        });
        plot1Button.addActionListener(e -> {
            plotPanel.removeAll();

            plotPanel.setLayout(new BorderLayout());
            XYDataset dataset = createDataset(geneticAlgorithm.getFirstPlotXYSeries());

            JFreeChart chart = createChart(dataset, "Wykres warto??ci funkcji od kolejnej iteracji", "Iteracja", "Warto???? funkcji");


            ChartPanel cp = new ChartPanel(chart);
            plotPanel.add(cp, BorderLayout.CENTER);
            plotPanel.validate();
            generateJpgFromJPanel("plot1.jpg");
        });
        plot2Button.addActionListener(e -> {
            plotPanel.removeAll();

            plotPanel.setLayout(new BorderLayout());
            XYDataset dataset = createDataset(geneticAlgorithm.getSecondPlotXYSeries());

            JFreeChart chart = createChart(dataset, "??rednia warto???? funkcji", "Iteracja", "Warto???? funkcji");

            ChartPanel cp = new ChartPanel(chart);
            plotPanel.add(cp, BorderLayout.CENTER);
            plotPanel.validate();
            generateJpgFromJPanel("plot2.jpg");
        });
        plot3Button.addActionListener(e -> {
            plotPanel.removeAll();

            plotPanel.setLayout(new BorderLayout());
            XYDataset dataset = createDataset(geneticAlgorithm.getThirdPlotXYSeries());

            JFreeChart chart = createChart(dataset, "Odchylenie standardowe od kolejnej iteracji", "Iteracja", "Odchylenie standardowe");

            ChartPanel cp = new ChartPanel(chart);
            plotPanel.add(cp, BorderLayout.CENTER);
            plotPanel.validate();
            generateJpgFromJPanel("plot3.jpg");
        });
    }

    void generateJpgFromJPanel(String name) {
        BufferedImage img = new BufferedImage(plotPanel.getWidth(), plotPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        plotPanel.print(img.getGraphics());
        try {
            ImageIO.write(img, "jpg", new File("plots/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XYDataset createDataset(XYSeries series) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String title, String xAxisLabel, String yAxisLabel) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                xAxisLabel,
                yAxisLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.setTitle(new TextTitle(title,
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Genetic algorithm");
        frame.setContentPane(new GeneticAlgorithmForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
