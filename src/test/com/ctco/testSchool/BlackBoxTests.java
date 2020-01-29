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
        Team team1 = new Team(); //will name every dev+tester as a separate team
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        Member developer2 = new Member(Member.type.DEV);
        Member tester2 = new Member(Member.type.TEST);

        team1.addMember(developer1);
        team1.addMember(tester1);

        Team team2 = new Team();//will name every dev+tester as a separate team
        team2.addMember(developer2);
        team2.addMember(tester2);

        Story story = new Story();
        story.setStoryPoints(8);
        story.setTestPoints(2);

        Story story2 = new Story();
        story2.setStoryPoints(8);
        story2.setTestPoints(3);

        team1.backlog = new ArrayList<>(); //creating list of backlog
        team1.backlog.add(story); //adding our story to list

        team2.backlog = new ArrayList<>();
        team2.backlog.add(story2);
        // as for now we should user canDeliver() to verify developers able to do their work in sprint and candeliverquality to verify testers work
        team1.sprintDays -= valueOf(story.storyPoints); //count how many days left for testing for the first story
        team2.sprintDays -= valueOf(story2.storyPoints); //count how many days left for testing for the second story
        assertTrue(team1.canDeliverQuality(), "Team  could not deliver when they should! "+"DEV had: "+story.storyPoints+" TEST had: "+story.testPoints);//canDeliverQuality doesn't count developer delivery
        assertFalse(team2.canDeliverQuality(), "Team could deliver when they should not! "+"DEV had: "+story2.storyPoints+" TEST had: "+story2.testPoints);//canDeliverQuality doesn't count developer delivery
    }

}
