package com.ctco.testSchool.tests;

import com.ctco.testSchool.Member;
import com.ctco.testSchool.Story;
import com.ctco.testSchool.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CanDeliverTest {

    @Test
    public void canDeliverStoryWithDefaultVelocity() {
        Team team = new Team();
        Member developer1 = new Member();
        developer1.codinSkills = true;
        Member developer2 = new Member();
        developer2.codinSkills = true;
        Member tester1 = new Member(); //actually no value of adding this member as he doesn't code and won't be used in calculations
        tester1.testingSkills = true; //he doesn't code and won't be used in calculations
        tester1.codinSkills = false; //to ignore this member out of calculation as he isn't a dev

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertTrue(team.canDeliver(), "Team (developers) couldn't deliver when they should");

    }

    @Test
    public void canDeliverDifferentVelocitySeveralStories() {
        Team team = new Team();
        Member developer1 = new Member();
        developer1.codinSkills = true;
        developer1.velocity = 5;
        Member developer2 = new Member();
        developer2.codinSkills = true;
        Member tester1 = new Member(); //actually no value of adding this member as he doesn't code and won't be used in calculations
        tester1.testingSkills = true; //he doesn't code and won't be used in calculations
        tester1.codinSkills = false; //to ignore this member out of calculation as he isn't a dev

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);

        Story story1 = new Story();
        story1.setStoryPoints(1);
        Story story2 = new Story();
        story2.setStoryPoints(3);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story1); //adding our story to list
        team.backlog.add(story2);

        assertTrue(team.canDeliver(), "Team (developers) couldn't deliver when they should");
        //but we are counting only 1 day , as there is no reference to sprint days in the canDeliver method
    }

    @Test
    public void cantDeliver() {
        Team team = new Team();
        Member developer1 = new Member();
        developer1.codinSkills = true;
        Member developer2 = new Member();
        developer2.codinSkills = true;
        Member tester1 = new Member(); //actually no value of adding this member as he doesn't code and won't be used in calculations
        tester1.testingSkills = true; //he doesn't code and won't be used in calculations
        tester1.codinSkills = false; //to ignore this member out of calculation as he isn't a dev

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);

        Story story1 = new Story();
        story1.setStoryPoints(3);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story1); //adding our story to list

        assertFalse(team.canDeliver(), "Team (developers) could deliver when they shouldn't");
    }
}
