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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    GeneticAlgorithm geneticAlgorithm;

    public GeneticAlgorithmForm() {
        populationSpinner.setModel(new SpinnerNumberModel(30, 0, 50, 1));
        epochsSpinner.setModel(new SpinnerNumberModel(500f, 0f, 1000f, 1f));
        selectionProbSpinner.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        crossoverProbSpinner.setModel(new SpinnerNumberModel(90, 0, 100, 1));
        mutationProbSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        inversionProbSpinner.setModel(new SpinnerNumberModel(10, 0, 100, 1));
        resultTextArea.setSize(100, 1000);
        acceptButton.addActionListener(e -> {
            geneticAlgorithm = new GeneticAlgorithm((Integer) populationSpinner.getValue(), Float.parseFloat(String.valueOf(epochsSpinner.getValue())),
                    Extrema.valueOf(extremaComboBox.getSelectedIndex()),
                    Selection.valueOf(selectionComboBox.getSelectedIndex()), (Integer) selectionProbSpinner.getValue(),
                    Crossover.valueOf(crossoverComboBox.getSelectedIndex()), (Integer) crossoverProbSpinner.getValue(),
                    Mutation.valueOf(mutationComboBox.getSelectedIndex()), (Integer) mutationProbSpinner.getValue(),
                    (Integer) inversionProbSpinner.getValue(),
                    eliteStrategyComboBox.getSelectedIndex());
            geneticAlgorithm.setResultTextArea(resultTextArea);
            geneticAlgorithm.run();
        });
        plot1Button.addActionListener(e -> {
            plotPanel.setLayout(new BorderLayout());
            XYDataset dataset = createDataset(geneticAlgorithm.getFirstPlotXYSeries());

            JFreeChart chart = createChart(dataset, "Wykres wartości funkcji od kolejnej iteracji", "Iteracja", "Wartość funkcji");


            ChartPanel cp = new ChartPanel(chart);
            plotPanel.add(cp, BorderLayout.CENTER);
            plotPanel.validate();
        });
        plot2Button.addActionListener(e -> {
            plotPanel.setLayout(new BorderLayout());
            XYDataset dataset = createDataset(geneticAlgorithm.getSecondPlotXYSeries());

            JFreeChart chart = createChart(dataset, "Średnia wartość funkcji", "Iteracja", "Wartość funkcji");

            ChartPanel cp = new ChartPanel(chart);
            plotPanel.add(cp, BorderLayout.CENTER);
            plotPanel.validate();
        });
        plot3Button.addActionListener(e -> {
            plotPanel.setLayout(new BorderLayout());
            XYDataset dataset = createDataset(geneticAlgorithm.getThirdPlotXYSeries());

            JFreeChart chart = createChart(dataset, "Odchylenie standardowe od kolejnej iteracji", "Iteracja", "Odchylenie standardowe");

            ChartPanel cp = new ChartPanel(chart);
            plotPanel.add(cp, BorderLayout.CENTER);
            plotPanel.validate();
        });
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
