package model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Battle {
	
	private int idBattle;
	private int idWinner;
	private int idLoser;
	private int idChosen;
	private String skill;	
	private String googleUidUser;
	private Date battleDate;
	
	public Battle(int idBattle, int idWinner, int idLoser, int idChosen, String skill, String googleUidUser,
			Date battleDate) {
		this.idBattle = idBattle;
		this.idWinner = idWinner;
		this.idLoser = idLoser;
		this.idChosen = idChosen;
		this.skill = skill;
		this.googleUidUser = googleUidUser;
		this.battleDate = battleDate;
	}

	public Battle() {
	}

	public int getIdBattle() {
		return idBattle;
	}

	public void setIdBattle(int idBattle) {
		this.idBattle = idBattle;
	}

	public int getIdWinner() {
		return idWinner;
	}

	public void setIdWinner(int idWinner) {
		this.idWinner = idWinner;
	}

	public int getIdLoser() {
		return idLoser;
	}

	public void setIdLoser(int idLoser) {
		this.idLoser = idLoser;
	}

	public int getIdChosen() {
		return idChosen;
	}

	public void setIdChosen(int idChosen) {
		this.idChosen = idChosen;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getGoogleUidUser() {
		return googleUidUser;
	}

	public void setGoogleUidUser(String googleUidUser) {
		this.googleUidUser = googleUidUser;
	}

	public Date getBattleDate() {
		return battleDate;
	}

	public void setBattleDate(Date battleDate) {
		this.battleDate = battleDate;
	}

}
