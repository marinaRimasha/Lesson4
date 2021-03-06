package com.ctco.testSchool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WhiteBoxTests {

    @Test
    public void canDeliverQuality_HappyPath() {
        Team team = new Team();

        Member tester1 = new Member(Member.type.TEST);
        team.addMember(tester1);

        Story story = new Story();
        story.setTestPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertTrue(team.canDeliverQuality(), "Team  couldn't deliver when they should"); //canDeliverQuality doesn't count developer deliver
    }

    @Test
    public void canDeliverQuality_Negative() {
        Team team = new Team();

        Member tester1 = new Member(Member.type.TEST);

        team.addMember(tester1);

        Story story = new Story();
        story.setTestPoints(13);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertFalse(team.canDeliverQuality(), "Team  could  deliver when they should not"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_SeveralStoriesAndTesters() {
        Team team = new Team();
        Member tester1 = new Member(Member.type.TEST);
        Member tester2 = new Member(Member.type.TEST);

        team.addMember(tester1);
        team.addMember(tester2);
        Story story = new Story();
        Story story2 = new Story();
        story.setTestPoints(8);
        story2.setTestPoints(10);

        team.backlog = new ArrayList<>();
        team.backlog.add(story);
        team.backlog.add(story2);

        assertTrue(team.canDeliverQuality(), "Team couldn't deliver when they should"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_noStoriesAdded() {
        Team team = new Team();

        Member tester1 = new Member(Member.type.TEST);

        team.addMember(tester1);
        team.backlog = new ArrayList<>();//empty backlog
        assertTrue(team.canDeliverQuality(), "Team couldn't deliver when they should"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_noVelocity() {
        Team team = new Team();
        Member tester1 = new Member(Member.type.TEST);

        tester1.velocity = 0;
        team.addMember(tester1);

        Story story = new Story();
        story.setTestPoints(2);

        team.backlog = new ArrayList<>();
        team.backlog.add(story);
        assertFalse(team.canDeliverQuality(), "Team could deliver when they should not"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_noTesters() {
        Team team = new Team();

        Story story = new Story();
        story.setTestPoints(2);

        team.backlog = new ArrayList<>();
        team.backlog.add(story);
        assertFalse(team.canDeliverQuality(), "Team could deliver when they should not"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_noSprint() {
        Team team = new Team();
        team.sprintDays = 0;
        Member tester1 = new Member(Member.type.DEV); //isnt used in canDeliverQuality
        team.addMember(tester1);//isnt used in canDeliverQuality

        Story story = new Story();
        story.setTestPoints(2);

        team.backlog = new ArrayList<>();
        team.backlog.add(story);

        assertFalse(team.canDeliverQuality(), "Team could deliver when they should not"); //canDeliverQuality doesn't count developer delivery
    }

    @Test
    public void canDeliverQuality_OneStoryTwoTesters() {
        Team team = new Team();

        Member tester1 = new Member(Member.type.TEST);
        Member tester2 = new Member(Member.type.TEST);

        team.addMember(tester1);
        team.addMember(tester2);
        Story story = new Story();
        story.setTestPoints(13);
        team.backlog = new ArrayList<>();
        team.backlog.add(story);

        assertTrue(team.canDeliverQuality(), "Team couldn't deliver when they should"); //canDeliverQuality doesn't count story per tester, counts storypoint / velocity of all
    }

    @Test
    public void canDeliverQuality_OneTesterTwoStories() {
        Team team = new Team();

        Member tester1 = new Member(Member.type.TEST);
        team.addMember(tester1);

        Story story = new Story();
        Story story2 = new Story();
        story.setTestPoints(8);
        story2.setTestPoints(2);
        team.backlog = new ArrayList<>();
        team.backlog.add(story);
        team.backlog.add(story2);

        assertTrue(team.canDeliverQuality(), "Team couldn't deliver when they should"); //canDeliverQuality doesn't count story per tester, counts storypoint / velocity of all
    }

}
