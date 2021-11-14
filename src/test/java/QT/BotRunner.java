package QT;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import jakarta.websocket.DeploymentException;


public class BotRunner {
	Bot_TreasureSolver btSolver = new Bot_TreasureSolver();
	
	@BeforeSuite
	public void QualithonStart() throws IOException, DeploymentException
	{
		btSolver.LaunchQualithonWebApp();
	}
	
	@Test
	public void T001_EnterQulaithon() throws IOException
	{
		btSolver.EnterToMaP();
	}

	@Test
	public void T002_StartTreasure() throws IOException
	{
		btSolver.StartTresure();
	}
	
	@Test
	public void T003_C1_Proceed() throws IOException, InterruptedException
	{
		btSolver.C1_Proceed();
	}
	
	@Test
	public void T004_VideoPlayer() throws IOException, InterruptedException
	{
		btSolver.C2_PlayVideo();
	}
	
	@Test
	public void T005_CrystalMaze() throws IOException, InterruptedException
	{
		btSolver.C3_CrystalMaze();
	}
	
	@Test
	public void T006_Maps() throws IOException, InterruptedException, AWTException
	{
		btSolver.Maps();
	}
	@Test
	public void T007_Captcha() throws IOException, InterruptedException, AWTException, WebDriverException
	{
		btSolver.Captcha();
	}

	@Test
	public void T008_WebSocket() throws IOException, InterruptedException, AWTException, WebDriverException, DeploymentException
	{
		btSolver.WebSocket();
	}
	
	@Test
	public void T009_ThanksScreen() throws IOException, InterruptedException
	{
	  btSolver.Submit();
		
//		Assert.assertEquals(btSolver.Submit(),"Congratulations!! You Found the Treasure");
		
	}

}
