package com.ctco.testSchool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WhiteBoxTests {


    @Test
    public void canDeliverQuality_HappyPath() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8); //doesn't bring any value to the test for now
        story.setTestPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertTrue(team.canDeliverQuality(), "Team  couldn't deliver when they should"); //canDeliverQuality doesn't count developer deliver
    }

    @Test
    public void canDeliverQuality_Negative() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(13); //doesn't bring any value to the test for now
        story.setTestPoints(13);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertFalse(team.canDeliverQuality(), "Team  could  deliver when they should not"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_SeveralStoriesAndTesters() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        Member tester2 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);
        team.addMember(tester2);
        Story story = new Story();
        Story story2 = new Story();
        story.setStoryPoints(13); //doesn't bring any value to the test for now
        story.setTestPoints(8);
        story2.setTestPoints(10);

        team.backlog = new ArrayList<>();
        team.backlog.add(story);
        team.backlog.add(story2);

        assertTrue(team.canDeliverQuality(), "Team couldn't deliver when they should"); //canDeliverQuality doesn't count developer delivery
    }

}
