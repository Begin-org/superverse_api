package dao;

import java.util.List;

import model.Battle;

public interface BattleDao {
	
	public void createBattle(Battle battle) throws Exception;
	
	public List<Battle> listBattles(String uidUser) throws Exception;
	
	public Battle getBattle(String uidUser, int idBattle) throws Exception;
	
	public void deleteBattle(int idBattle) throws Exception;
	
}
