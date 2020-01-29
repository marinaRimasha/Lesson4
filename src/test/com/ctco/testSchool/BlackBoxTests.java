package com.ctco.testSchool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackBoxTests {

    @Test
    public void canDeliverQuality_HappyPath() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8);//doesn't bring any value to the test for now
        story.setTestPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertTrue(team.canDeliverQuality(), "Team  couldn't deliver when they should");//canDeliverQuality doesn't count developer delivery
       // as for now we should user canDeliver() to verify developers able to do their work in sprint and candeliverquality to verify testers work
        team.sprintDays -= valueOf(story.storyPoints); //count how many days left for testing
        assertTrue(team.canDeliverQuality(), "Team  couldn't deliver when they should");//canDeliverQuality doesn't count developer delivery
    }


    @Test
    public void canDeliverQuality_Negative() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8);//doesn't bring any value to the test for now
        story.setTestPoints(5);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertTrue(team.canDeliverQuality(), "Team  couldn't deliver when they should");//canDeliverQuality doesn't count developer delivery
        // as for now we should user canDeliver() to verify developers able to do their work in sprint and candeliverquality to verify testers work
        team.sprintDays -= valueOf(story.storyPoints); //count how many days left for testing
        assertFalse(team.canDeliverQuality(), "Team  could deliver when they should not");//canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_SeveralStoriesBiggerTeam() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8);//doesn't bring any value to the test for now
        story.setTestPoints(5);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertTrue(team.canDeliverQuality(), "Team  couldn't deliver when they should");//canDeliverQuality doesn't count developer delivery
        // as for now we should user canDeliver() to verify developers able to do their work in sprint and candeliverquality to verify testers work
        team.sprintDays -= valueOf(story.storyPoints); //count how many days left for testing
        assertFalse(team.canDeliverQuality(), "Team  could deliver when they should not");//canDeliverQuality doesn't count developer delivery
    }

}
