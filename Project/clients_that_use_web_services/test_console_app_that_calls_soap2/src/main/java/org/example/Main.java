package org.example;

import org.example.soap.BusPass;
import org.example.soap.BusPassService;
import org.example.soap.BusPassServiceImplService;
import org.example.soapperfume.SkillsAssessmentSquashTechnical;
import org.example.soapperfume.SkillsAssessmentSquashTechnicalService;
import org.example.soapperfume.SkillsAssessmentSquashTechnicalServiceImplService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello, I'm calling the soap service!");
//        BusPassServiceImplService busPassServiceImplService = new BusPassServiceImplService();
//        BusPassService busPassService = busPassServiceImplService.getBusPassServiceImplPort();
//        System.out.println("count="+busPassService.getCount());
//
//        List<BusPass> theList = busPassService.getBusPasses();
//        for(BusPass current: theList){
//            System.out.println(current);
//        }

               SkillsAssessmentSquashTechnicalServiceImplService skillsAssessmentSquashTechnicalServiceImplService = new SkillsAssessmentSquashTechnicalServiceImplService();
        SkillsAssessmentSquashTechnicalService skillsAssessmentSquashTechnicalService = skillsAssessmentSquashTechnicalServiceImplService.getSkillsAssessmentSquashTechnicalServiceImplPort();

        List<SkillsAssessmentSquashTechnical> theList = skillsAssessmentSquashTechnicalService.getAssessments();
        for(SkillsAssessmentSquashTechnical current: theList){
            System.out.println(current);
        }


    }
}