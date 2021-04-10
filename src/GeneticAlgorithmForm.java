import comparing.Extrema;
import crossing.Crossover;
import mutation.Mutation;
import selection.Selection;

import javax.swing.*;
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
    private JLabel inversionProbLabel;
    private JLabel eliteStrategyLabel;
    private JComboBox inversionComboBox;
    private JComboBox eliteStrategyComboBox;
    private JTextArea resultTextArea;

    public GeneticAlgorithmForm() {
        populationSpinner.setModel(new SpinnerNumberModel(30,0,50,1));
        epochsSpinner.setModel(new SpinnerNumberModel(500f,0f,1000f,1f));
        selectionProbSpinner.setModel(new SpinnerNumberModel(5,0,10,1));
        crossoverProbSpinner.setModel(new SpinnerNumberModel(90,0,100,1));
        mutationProbSpinner.setModel(new SpinnerNumberModel(10,0,100,1));
        inversionProbSpinner.setModel(new SpinnerNumberModel(10,0,100,1));
        resultTextArea.setSize(100,1000);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm((Integer) populationSpinner.getValue(), Float.parseFloat(String.valueOf(epochsSpinner.getValue())),
                        Extrema.valueOf(extremaComboBox.getSelectedIndex()),
                        Selection.valueOf(selectionComboBox.getSelectedIndex()), (Integer) selectionProbSpinner.getValue(),
                        Crossover.valueOf(crossoverComboBox.getSelectedIndex()), (Integer) crossoverProbSpinner.getValue(),
                        Mutation.valueOf(mutationComboBox.getSelectedIndex()), (Integer) mutationProbSpinner.getValue(),
                        (Integer) inversionProbSpinner.getValue(),
                        eliteStrategyComboBox.getSelectedIndex());
                geneticAlgorithm.setResultTextArea(resultTextArea);
                geneticAlgorithm.run();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Genetic algorithm");
        frame.setContentPane(new GeneticAlgorithmForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
