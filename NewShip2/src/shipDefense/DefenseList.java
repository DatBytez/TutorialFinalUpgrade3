package shipDefense;

import ship.ProgressLevel;
import ship.Severity;
import ship.Tech;
import shipHelperz.Moneyz;

public enum DefenseList {
		
		Chaff(					"Chaff",					ProgressLevel.PL6, Tech.N, /*Hull*/1, /*Power*/0, Moneyz.money(50, "K"), /*Cover*/100),//+1 Step Missiles/Sensors
		
		DamageControl(			"Damage Control",			ProgressLevel.PL6, Tech.N, /*Hull*/0, /*Power*/0, Moneyz.money(0, "K"), /*Cover*/0),//Hull is .05% Power is 1/hull Cost is 100K/hull //-2 Bonus to Damage Checks
		
		DecoyDrone(				"Decoy Drone",				ProgressLevel.PL6, Tech.C, /*Hull*/1, /*Power*/1, Moneyz.money(600, "K"), /*Cover*/100),//Adds 3 drones
		
		Jammer(					"Jammer",					ProgressLevel.PL6, Tech.N, /*Hull*/1, /*Power*/1, Moneyz.money(100, "K"), /*Cover*/100),//+2 Missiles/Sensors
		
		MagneticScreen(			"Magnetic Screen",			ProgressLevel.PL6, Tech.S, /*Hull*/4, /*Power*/2, Moneyz.money(400, "K"), /*Cover*/20),//+2 Missiles/Projectiles
		
		StealthHull(			"Stealth Hull",				ProgressLevel.PL6, Tech.S, /*Hull*/2, /*Power*/1, Moneyz.money(500, "K"), /*Cover*/50),//+2 Sensors
		
		DefenseNetwork(			"Defense Network",			ProgressLevel.PL7, Tech.C, /*Hull*/2, /*Power*/2, Moneyz.money(500, "K"), /*Cover*/100),//Add F to Tech; //Special
		
		DeflectionInducer(		"Deflection Inducer",		ProgressLevel.PL7, Tech.G, /*Hull*/1, /*Power*/2, Moneyz.money(500, "K"), /*Cover*/20),//+2 Penalty to enemy Attacks
		
		ParticleScreen(			"Particle Screen",			ProgressLevel.PL7, Tech.Q, /*Hull*/2, /*Power*/3, Moneyz.money(750, "K"), /*Cover*/20),//+Armor d4(LI),d4(HI),d6(En)
		
		StealthShield(			"Stealth Shield",			ProgressLevel.PL7, Tech.M, /*Hull*/2, /*Power*/2, Moneyz.money(2, "M"), /*Cover*/100),//+3 Missiles/Sensors
		
		StardriveScrambler(		"Stardrive Scrambler",		ProgressLevel.PL7, Tech.G, /*Hull*/1, /*Power*/2, Moneyz.money(200, "K"), /*Cover*/100),//+4 Drive Detectors
		
		RepairBots(				"Repair Bots",				ProgressLevel.PL7, Tech.C, /*Hull*/0, /*Power*/1, Moneyz.money(500, "K"), /*Cover*/0),//Hull is .05% Power is 1/hull Cost is 100K/hull //-3 Bonus to Damage Checks
		
		AblativeShieldGenerator("Ablative Shield Generator",ProgressLevel.PL8, Tech.N, /*Hull*/1, /*Power*/2, Moneyz.money(500, "K"), /*Cover*/20),//capacitor or compiler required
		
		AblativeShieldCapacitor("Ablative Shield Capacitor",ProgressLevel.PL8, Tech.N, /*Hull*/1, /*Power*/0, Moneyz.money(100, "K"), /*Cover*/0),//10 shield points
		
		EnergyCompiler(			"Energy Compiler",			ProgressLevel.PL8, Tech.X, /*Hull*/1, /*Power*/0, Moneyz.money(500, "K"), /*Cover*/0),//20 shield points
		
		CloakingUnit(			"Cloaking Unit",			ProgressLevel.PL8, Tech.M, /*Hull*/1, /*Power*/5, Moneyz.money(1, "M"), /*Cover*/100),//+4 Missiles/Sensors
		
		Displacer(				"Displacer",				ProgressLevel.PL8, Tech.T, /*Hull*/2, /*Power*/3, Moneyz.money(1, "M"), /*Cover*/20),//+3 Penalty to enemy Attacks
		
		NaniteRepairArray(		"Nanite Repair Array",		ProgressLevel.PL9, Tech.C, /*Hull*/0, /*Power*/2, Moneyz.money(500, "K"), /*Cover*/50);//Hull is .05% Power is 2/hull Cost is 1M/hull //-5 Bonus to Damage Checks
				
		String name;
		ProgressLevel level;
		Tech tech;
		int hull, power, cost, coverage;

		DefenseList(String name, ProgressLevel progressLevel, Tech tech, int hull, int power, int cost, int coverage){
			this.name = name;
			this.level = progressLevel;
			this.tech = tech;
			this.hull = hull;
			this.power = power;
			this.cost = cost;
			this.coverage = coverage;
		}
	}
