package com.ctco.testSchool;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BlackBoxTests {


    @Test
    public void canDeliverQuality_NotEnoughTestingTime() { //can't deliver as testers will not have enough time, there is only 1 tester per story
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member developer2 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        Member tester2 = new Member(Member.type.TEST);

        developer1.velocity = 2.0;
        tester2.velocity = 2.0;

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);
        team.addMember(tester2);

        Story story = new Story();
        story.setStoryPoints(8); //for first developer, they work at the same time
        story.setTestPoints(2); //

        Story story2 = new Story();
        story2.setStoryPoints(8); //for second developer, they work at the same time
        story2.setTestPoints(6); //

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list
        team.backlog.add(story2);

        try {
            assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("Expected exception: " +IllegalArgumentException.class.getSimpleName() + " has not been thrown");

        } catch (IllegalArgumentException e) {
            assertEquals("Velocity can't be more than 1", e.getMessage(), "Exception not caught");
        }
    }

    @Test
    public void canDeliverQuality_OneDevAbsent() { //Developer got sick, the other took it up, could deliver
        Team team = new Team();

        Member developer1 = new Member(Member.type.DEV);
        Member developer2 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        developer1.velocity = 0;

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);

        Story story = new Story();
        Story story2 = new Story();
        story.setStoryPoints(2);//doesn't bring any value to the test for now
        story.setTestPoints(1);
        story2.setStoryPoints(4);
        story2.setTestPoints(3);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        try {
            assertEquals(true, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("Expected exception: " +IllegalArgumentException.class.getSimpleName() + " has not been thrown");

        } catch (IllegalArgumentException e) {
            assertEquals("Velocity should be positive", e.getMessage(), "Exception not caught");
        }
    }

    @Test
    public void canDeliverQuality_OneTesterAbsent() { //Tester got sick, the other took it up, could deliver
        Team team = new Team();

        Member developer1 = new Member(Member.type.DEV);
        Member developer2 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        Member tester2 = new Member(Member.type.TEST);
        tester1.velocity = 0;

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);
        team.addMember(tester2);

        Story story = new Story();
        Story story2 = new Story();
        story.setStoryPoints(6);//doesn't bring any value to the test for now
        story.setTestPoints(2);
        story2.setStoryPoints(6);
        story2.setTestPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        try {
            assertEquals(true, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("Expected exception: " +IllegalArgumentException.class.getSimpleName() + " has not been thrown");

        } catch (IllegalArgumentException e) {
            assertEquals("Velocity should be positive", e.getMessage(), "Exception not caught");
        }
    }


    @Test
    public void canDeliverQuality_ZeroDevVelocity() { //Developer got sick, spilled story
        Team team = new Team();

        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        developer1.velocity = 0;
        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8);//doesn't bring any value to the test for now
        story.setTestPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        try {
            assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("Expected exception: " +IllegalArgumentException.class.getSimpleName() + " has not been thrown");

        } catch (IllegalArgumentException e) {
            assertEquals("Velocity should be positive", e.getMessage(), "Exception not caught");
        }
    }

    @Test
    public void canDeliverQuality_ForgotToSetSprintDays() { //forgot to set sprint days //
        Team team = new Team();
        team.sprintDays = 0;
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(2);//doesn't bring any value to the test for now
        story.setTestPoints(1);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        try {
            assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");
            fail("Check sprint is >2");

        } catch (IllegalArgumentException e) {
            assertEquals("Sprint should be at least two days long", e.getMessage(), "Exception not caught");
        }
    }

    @Test
    public void canDeliverQuality_HappyPath() { //8 devpoints, 2 testpoints, 10 days enough
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

        assertEquals(true, team.canDeliverQuality(), "Team  couldn't deliver when they should");
    }

    @Test
    public void canDeliverQuality_LessVelocityforTester() { //8 devpoints, 2 testpoints, 10 days not enough as tester works halftime
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        tester1.velocity = 0.5;

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8);//doesn't bring any value to the test for now
        story.setTestPoints(2);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertEquals(true, team.canDeliverQuality(), "Team  couldn't deliver when they should");
    }

    @Test
    public void canDeliverQuality_Negative() { //8 devpoints, 5 testpoints, only 10 days - not enough
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8);
        story.setTestPoints(5);

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list
        assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");
    }

    @Test
    public void canDeliverQuality_SeveralStoriesOneTesterTeam() { //2 stories for 5 devpoints, 1 and 4 testpoints - having 2 devs, 1 tester
        Team team = new Team();

        Member developer1 = new Member(Member.type.DEV);
        Member developer2 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(5); //for first developer, they work at the same time
        story.setTestPoints(1); //tester has 1 day for testing after dev finished

        Story story2 = new Story();
        story2.setStoryPoints(5); //for second developer, they work at the same time
        story2.setTestPoints(4); // tester has exactly 4 days left after he tested the 1st story

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list
        team.backlog.add(story2);
        assertEquals(true, team.canDeliverQuality(), "Team  could not deliver when they should");
    }

    @Test
    public void canDeliverQuality_SeveralStoriesNegative() { //can't deliver as tester will not have enough time to test both stories
        Team team = new Team();

        Member developer1 = new Member(Member.type.DEV);
        Member developer2 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);

        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(8); //for first developer, they work at the same time
        story.setTestPoints(3); //

        Story story2 = new Story();
        story2.setStoryPoints(3); //for second developer, they work at the same time
        story2.setTestPoints(2); //

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list
        team.backlog.add(story2);
        assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");
    }


    @Test
    public void canDeliverQuality_NotEnoughTimeForDevelopers() { //can't deliver as developers will not have enough time, there is only 1 developer per story //check this one
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member developer2 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        Member tester2 = new Member(Member.type.TEST);

        developer2.velocity = 2.0;
        team.addMember(developer1);
        team.addMember(developer2);
        team.addMember(tester1);
        team.addMember(tester2);

        Story story = new Story();
        story.setStoryPoints(8); //for first developer, they work at the same time
        story.setTestPoints(2); //

        Story story2 = new Story();
        story2.setStoryPoints(13); //for second developer, they work at the same time
        story2.setTestPoints(6); //

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list
        team.backlog.add(story2);

        try {
            assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");

        } catch (IllegalArgumentException e) {
            assertEquals("Velocity can't be more than 1", e.getMessage(), "Exception not caught");
        }
    }


    @Test
    public void canDeliverQuality_NoTesters() { //can't deliver as no testers available
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);

        team.addMember(developer1);

        Story story = new Story();
        story.setStoryPoints(8); //for first developer, they work at the same time
        story.setTestPoints(2); //

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list

        assertEquals(false, team.canDeliverQuality(), "Team  could deliver when they should not");
    }

    @Test
    public void canDeliverQuality_ZeroPoints() {
        Team team = new Team();
        Member developer1 = new Member(Member.type.DEV);
        Member tester1 = new Member(Member.type.TEST);
        team.addMember(developer1);
        team.addMember(tester1);

        Story story = new Story();
        story.setStoryPoints(0); //for first developer, they work at the same time
        story.setTestPoints(0); //

        team.backlog = new ArrayList<>(); //creating list of backlog
        team.backlog.add(story); //adding our story to list
        assertEquals(true, team.canDeliverQuality(), "Team  could deliver when they should not");
    }


    @Test
    public void Test1() {
        assertEquals(3, Team.getPrimeNumberClosesTo(3), "");
    }

    @Test
    public void Test2() {
        assertEquals(7, Team.getPrimeNumberClosesTo(8), "returned incorrect number");
    }

    @Test
    public void Test3() {
        int result = Team.getPrimeNumberClosesTo(11);
//        if ((result == 7 )|| (result == 11 )) {
//            assertTrue(true);
//        } else {
//            fail("Returned "+result+" expected was 7 or 11");
//        }
        assertTrue(((result == 7) || (result == 11)), "Returned " + result + " expected was 7 or 11");
    }

//    @Test
//    public void TestHelloWorld() {
//        assertEquals("Hello world!",  Team.getHelloWorldText(), "Didnt return Hello World as it is 17 now");
//    }

    @Test
    public void TestDayPhase() {
        int localHour = LocalDateTime.now().getHour();
        if ((localHour == 8) || (localHour == 9) || (localHour == 10) || (localHour == 11)) {
            assertEquals("Good morning world!", Team.getHelloWorldText(), "Incorrect message: " + Team.getHelloWorldText());
        } else if ((localHour == 12) || (localHour == 13) || (localHour == 14) || (localHour == 15) || (localHour == 16)) {
            assertEquals("Good day world!", Team.getHelloWorldText(), "Incorrect message: " + Team.getHelloWorldText());
        } else if (localHour == 17) {
            assertEquals("Hello world!", Team.getHelloWorldText(), "Incorrect message: " + Team.getHelloWorldText());
        } else {
            assertEquals("Good night world!", Team.getHelloWorldText(), "Incorrect message: " + Team.getHelloWorldText());
        }
    }

}
