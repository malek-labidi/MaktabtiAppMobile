/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.genesisteam.maktabti.gui.competition;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.genesisteam.maktabti.entities.Question;
import com.genesisteam.maktabti.gui.BaseForm;
import com.genesisteam.maktabti.gui.Home;
import com.genesisteam.maktabti.services.QuestionService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class getQuestions extends BaseForm {

    QuestionService cs = QuestionService.getInstance();
    private ArrayList<ButtonGroup> buttonGroups;
    private List<Question> questions;
    private Resources theme;

    public getQuestions(List<Question> questions, Resources res) {
        // setSingleLineTextArea(false);
        this.questions = questions;
        this.buttonGroups = new ArrayList<>();
        setTitle("Quiz");
        setScrollableY(true);
        Image backIcon = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));

        Command back = new Command("", backIcon) {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new GetCompetitions(res).showBack();
            }
        };
        getToolbar().addCommandToLeftBar(back);

        for (Question question : questions) {
            Container questionContainer = new Container(new BorderLayout());

            SpanLabel questionLabel = new SpanLabel(question.getQuestion());
            questionLabel.setTextUIID("MultiLine");
            questionLabel.setUIID("Label");
            questionLabel.setScrollVisible(false);
            questionContainer.add(BorderLayout.NORTH, questionLabel);

            RadioButton choice1Button = new RadioButton(question.getChoix1());
            RadioButton choice2Button = new RadioButton(question.getChoix2());
            RadioButton choice3Button = new RadioButton(question.getChoix3());
            //choice1Button.setAutoSizeMode(true);

            ButtonGroup choiceGroup = new ButtonGroup(choice1Button, choice2Button, choice3Button);
            buttonGroups.add(choiceGroup);

            Container choicesContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            choicesContainer.add(choice1Button);
            choicesContainer.add(choice2Button);
            choicesContainer.add(choice3Button);
            questionContainer.add(BorderLayout.CENTER, choicesContainer);

            // Add question container to the form
            add(questionContainer);
        }

        // Add submit button
        Button submitButton = new Button("Submit");
        submitButton.addActionListener(evt -> {
            int score = calculateScore();
            ToastBar.showMessage("Your score is " + score, FontImage.MATERIAL_CHECK);
            // Dialog.show("Score",  + score, "OK", null);
        });
        add(submitButton);

    }

    private int calculateScore() {
        int score = 0;
        int index = 0;
        for (Question question : questions) {
            ButtonGroup choiceGroup = buttonGroups.get(index);
            RadioButton selectedButton = choiceGroup.getSelected();
            if (selectedButton != null && selectedButton.getText().equals(question.getReponseCorrect())) {
                score++;
            }
            index++;
        }
        return score;
    }

}
