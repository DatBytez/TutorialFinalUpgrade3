package shipWeapons;

import java.util.ArrayList;

import helpz.MyShipObject;
import shipArmor.Armor;
import shipHelperz.Moneyz;
import shipfight.ProgressLevel;
import shipfight.Severity;
import shipfight.Tech;

public enum WeaponList {
	//BEAM
	Laser(			"Laser", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/1, /*Power*/2, Moneyz.money(100, "K"), /*Acc*/-2, new FireRange(1,2,3), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(1,4,Severity.STUN,1,4,Severity.WOUND,3,6,Severity.WOUND), new WeaponModes(true,false,false,false)),
	
	IRLaser(		"IR Laser", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/2, /*Power*/2, Moneyz.money(250, "K"), /*Acc*/-2, new FireRange(1,2,3), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(2,5,Severity.STUN,2,5,Severity.WOUND,2,7,Severity.WOUND), new WeaponModes(true,false,false,false)),
	
	XRayLaser(		"X-Ray Laser", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/2, /*Power*/2, Moneyz.money(350, "K"), /*Acc*/-2, new FireRange(1,2,3), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(2,7,Severity.STUN,3,6,Severity.WOUND,1,4,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	HeavyLaser(		"Heavy Laser", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/5, /*Power*/5, Moneyz.money(2, "M"), /*Acc*/-1, new FireRange(1,3,5), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(3,8,Severity.STUN,3,8,Severity.WOUND,1,6,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	NeutronGun(		"Neutron Gun", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/7, /*Power*/7, Moneyz.money(10, "M"), /*Acc*/0, new FireRange(1,3,5), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(2,8,Severity.WOUND,4,10,Severity.WOUND,2,8,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	FusionLaser(	"Fusion Laser", WeaponType.BEAM, ProgressLevel.PL6, Tech.F, /*Hull*/12, /*Power*/12, Moneyz.money(20, "M"), /*Acc*/+1, new FireRange(2,4,6), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,12,Severity.WOUND,4,14,Severity.WOUND,2,12,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	Graser(			"Graser", WeaponType.BEAM, ProgressLevel.PL6, Tech.Q, /*Hull*/15, /*Power*/15, Moneyz.money(40, "M"), /*Acc*/+2, new FireRange(3,6,9), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(3,12,Severity.STUN,2,8,Severity.MORTAL,3,12,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	HeavyNeutronGun("Heavy Neutron Gun", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/22, /*Power*/22, Moneyz.money(60, "M"), /*Acc*/+3, new FireRange(3,6,9), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(2,12,Severity.STUN,2,12,Severity.MORTAL,4,14,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	HydrogenBore(	"Hydrogen Bore", WeaponType.BEAM, ProgressLevel.PL6, Tech.N, /*Hull*/50, /*Power*/60, Moneyz.money(100, "M"), /*Acc*/+3, new FireRange(4,8,12), DamageType.ENERGY, Firepower.SUPERHEAVY,
					new DamageSet(3,12,Severity.WOUND,3,12,Severity.MORTAL,3,12,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	PlasmaCannon(	"Plasma Cannon", WeaponType.BEAM, ProgressLevel.PL7, Tech.F, /*Hull*/3, /*Power*/3, Moneyz.money(400, "K"), /*Acc*/-2, new FireRange(1,2,4), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(3,8,Severity.WOUND,3,10,Severity.WOUND,2,7,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	ParticleBeam(	"Particle Beam", WeaponType.BEAM, ProgressLevel.PL7, Tech.N, /*Hull*/4, /*Power*/5, Moneyz.money(500, "K"), /*Acc*/-2, new FireRange(2,4,6), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(4,9,Severity.STUN,2,5,Severity.MORTAL,4,7,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	HeavyParticleBeam("Heavy Particle Beam", WeaponType.BEAM, ProgressLevel.PL7, Tech.N, /*Hull*/6, /*Power*/8, Moneyz.money(4, "M"), /*Acc*/-1, new FireRange(2,4,6), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(4,15,Severity.STUN,2,7,Severity.MORTAL,4,9,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	HeavyPlasmaBeam("Heavy Plasma Beam", WeaponType.BEAM, ProgressLevel.PL7, Tech.F, /*Hull*/8, /*Power*/8, Moneyz.money(12, "M"), /*Acc*/0, new FireRange(2,4,8), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(3,10,Severity.WOUND,3,14,Severity.WOUND,3,10,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	MatterBeam(		"Matter Beam", WeaponType.BEAM, ProgressLevel.PL7, Tech.A, /*Hull*/11, /*Power*/11, Moneyz.money(20, "M"), /*Acc*/0, new FireRange(2,5,10), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(3,13,Severity.WOUND,3,17,Severity.WOUND,2,16,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	FusionBeam(		"Fusion Beam", WeaponType.BEAM, ProgressLevel.PL7, Tech.F, /*Hull*/15, /*Power*/15, Moneyz.money(25, "M"), /*Acc*/+1, new FireRange(3,6,12), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(3,14,Severity.WOUND,5,16,Severity.WOUND,6,13,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	QuantumCannon(	"Quantum Cannon", WeaponType.BEAM, ProgressLevel.PL7, Tech.Q, /*Hull*/18, /*Power*/18, Moneyz.money(50, "M"), /*Acc*/+2, new FireRange(4,8,12), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,16,Severity.WOUND,2,12,Severity.MORTAL,2,8,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	BosonGun(		"Boson Gun", WeaponType.BEAM, ProgressLevel.PL7, Tech.N, /*Hull*/20, /*Power*/20, Moneyz.money(80, "M"), /*Acc*/+3, new FireRange(4,8,12), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(3,18,Severity.WOUND,3,18,Severity.MORTAL,4,24,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	HeavyMatterBeam("Heavy Matter Beam", WeaponType.BEAM, ProgressLevel.PL7, Tech.A, /*Hull*/24, /*Power*/24, Moneyz.money(100, "M"), /*Acc*/+3, new FireRange(4,8,16), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(3,13,Severity.MORTAL,3,17,Severity.MORTAL,2,16,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	FusionBore(		"Fusion Bore", WeaponType.BEAM, ProgressLevel.PL7, Tech.F, /*Hull*/60, /*Power*/75, Moneyz.money(200, "M"), /*Acc*/+3, new FireRange(5,10,15), DamageType.ENERGY, Firepower.SUPERHEAVY,
					new DamageSet(3,12,Severity.MORTAL,3,12,Severity.CRITICAL,3,18,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	MaserCannon(	"Maser Cannon", WeaponType.BEAM, ProgressLevel.PL8, Tech.N, /*Hull*/4, /*Power*/3, Moneyz.money(400, "K"), /*Acc*/-3, new FireRange(1,3,5), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(3,8,Severity.WOUND,2,5,Severity.MORTAL,3,9,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	KineticLance(	"Kinetic Lance", WeaponType.BEAM, ProgressLevel.PL8, Tech.X, /*Hull*/2, /*Power*/1, Moneyz.money(750, "K"), /*Acc*/-3, new FireRange(2,4,6), DamageType.HIIMPACT, Firepower.SMALL,
					new DamageSet(2,5,Severity.WOUND,2,8,Severity.WOUND,4,7,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	PulseMaser(		"Pulse Maser", WeaponType.BEAM, ProgressLevel.PL8, Tech.N, /*Hull*/6, /*Power*/4, Moneyz.money(4, "M"), /*Acc*/-1, new FireRange(2,5,10), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(5,12,Severity.WOUND,3,10,Severity.MORTAL,3,14,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	EMCannon(		"EM Cannon", WeaponType.BEAM, ProgressLevel.PL8, Tech.N, /*Hull*/8, /*Power*/8, Moneyz.money(10, "M"), /*Acc*/-1, new FireRange(3,6,12), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(3,18,Severity.STUN,4,24,Severity.STUN,4,32,Severity.STUN), new WeaponModes(true,true,false,false)),
	
	DarkFusionGun(	"Dark Fusion Gun", WeaponType.BEAM, ProgressLevel.PL8, Tech.D, /*Hull*/15, /*Power*/15, Moneyz.money(40, "M"), /*Acc*/0, new FireRange(4,8,12), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,12,Severity.WOUND,3,12,Severity.MORTAL,3,9,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	GatlingMaser(	"Gatling Maser", WeaponType.BEAM, ProgressLevel.PL8, Tech.N, /*Hull*/18, /*Power*/36, Moneyz.money(50, "M"), /*Acc*/+1, new FireRange(3,7,14), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(5,12,Severity.WOUND,3,10,Severity.MORTAL,3,14,Severity.MORTAL), new WeaponModes(true,true,true,true)),
	
	WeakForceGun(	"Weak Force Gun", WeaponType.BEAM, ProgressLevel.PL8, Tech.M, /*Hull*/25, /*Power*/25, Moneyz.money(120, "M"), /*Acc*/+2, new FireRange(5,10,15), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(4,16,Severity.WOUND,4,16,Severity.MORTAL,4,12,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	StrongForceGun(	"Strong Force Gun", WeaponType.BEAM, ProgressLevel.PL8, Tech.M, /*Hull*/32, /*Power*/32, Moneyz.money(150, "M"), /*Acc*/+3, new FireRange(5,10,20), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(5,20,Severity.MORTAL,3,12,Severity.CRITICAL,4,24,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	ZeroBore(		"Zero Bore", WeaponType.BEAM, ProgressLevel.PL8, Tech.Q, /*Hull*/100, /*Power*/120, Moneyz.money(300, "M"), /*Acc*/+3, new FireRange(6,12,18), DamageType.ENERGY, Firepower.SUPERHEAVY,
					new DamageSet(4,24,Severity.MORTAL,3,18,Severity.CRITICAL,3,24,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	Blacklaser(		"Blacklaser", WeaponType.BEAM, ProgressLevel.PL9, Tech.D, /*Hull*/3, /*Power*/3, Moneyz.money(1, "M"), /*Acc*/-3, new FireRange(2,4,6), DamageType.ENERGY, Firepower.SMALL,
					new DamageSet(1,8,Severity.WOUND,1,12,Severity.WOUND,1,8,Severity.MORTAL), new WeaponModes(true,true,true,true)),
	
	TachyonGun(		"Tachyon Gun", WeaponType.BEAM, ProgressLevel.PL9, Tech.X, /*Hull*/12, /*Power*/12, Moneyz.money(40, "M"), /*Acc*/0, new FireRange(2,4,6), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(4,18,Severity.WOUND,2,16,Severity.MORTAL,2,9,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	StringProjector("String Projector", WeaponType.BEAM, ProgressLevel.PL9, Tech.Q, /*Hull*/24, /*Power*/30, Moneyz.money(200, "M"), /*Acc*/+2, new FireRange(6,12,24), DamageType.LOWIMPACT, Firepower.HEAVY,
					new DamageSet(4,32,Severity.WOUND,3,24,Severity.MORTAL,3,24,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	//PROJECTILE
	
	PointDefense(	"Point Defense Gun", WeaponType.PROJECTILE, ProgressLevel.PL6, Tech.N, /*Hull*/1, /*Power*/0, Moneyz.money(200, "K"), /*Acc*/-1, new FireRange(1,2,3), DamageType.HIIMPACT, Firepower.GOOD,
					new DamageSet(1,4,Severity.STUN,1,4,Severity.WOUND,3,6,Severity.WOUND), new WeaponModes(true,false,true,true)),
	
	RailCannon(		"Rail Cannon", WeaponType.PROJECTILE, ProgressLevel.PL6, Tech.N, /*Hull*/4, /*Power*/3, Moneyz.money(500, "K"), /*Acc*/0, new FireRange(1,2,5), DamageType.HIIMPACT, Firepower.SMALL,
					new DamageSet(2,8,Severity.STUN,3,8,Severity.WOUND,2,5,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	NeedleDriver(	"Needle Driver", WeaponType.PROJECTILE, ProgressLevel.PL6, Tech.N, /*Hull*/6, /*Power*/6, Moneyz.money(2, "M"), /*Acc*/+1, new FireRange(1,3,5), DamageType.HIIMPACT, Firepower.LIGHT,
					new DamageSet(2,7,Severity.WOUND,4,9,Severity.WOUND,4,7,Severity.MORTAL), new WeaponModes(true,false,false,true)),
	
	GaussGun(		"Gauss Gun", WeaponType.PROJECTILE, ProgressLevel.PL6, Tech.S, /*Hull*/8, /*Power*/6, Moneyz.money(5, "M"), /*Acc*/+2, new FireRange(2,4,6), DamageType.HIIMPACT, Firepower.MEDIUM,
					new DamageSet(2,7,Severity.WOUND,3,8,Severity.MORTAL,2,5,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	HiRailGun(		"Hi-Velocity Rail Gun", WeaponType.PROJECTILE, ProgressLevel.PL6, Tech.N, /*Hull*/20, /*Power*/16, Moneyz.money(50, "M"), /*Acc*/+4, new FireRange(3,6,12), DamageType.HIIMPACT, Firepower.HEAVY,
					new DamageSet(3,10,Severity.WOUND,3,10,Severity.MORTAL,3,8,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	MassCannon(		"Mass Cannon", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.G, /*Hull*/2, /*Power*/3, Moneyz.money(300, "K"), /*Acc*/-1, new FireRange(1,3,5), DamageType.LOWIMPACT, Firepower.SMALL,
					new DamageSet(3,8,Severity.STUN,2,7,Severity.WOUND,4,9,Severity.WOUND), new WeaponModes(true,true,false,false)),
	
	HeavyMassCannon("Heavy Mass Cannon", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.G, /*Hull*/5, /*Power*/6, Moneyz.money(2, "M"), /*Acc*/0, new FireRange(2,4,6), DamageType.LOWIMPACT, Firepower.LIGHT,
					new DamageSet(2,12,Severity.STUN,2,12,Severity.WOUND,3,18,Severity.WOUND), new WeaponModes(true,true,false,false)),
	
	Accelerator(	"Accelerator", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.N, /*Hull*/9, /*Power*/7, Moneyz.money(10, "M"), /*Acc*/+1, new FireRange(3,5,7), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,12,Severity.STUN,2,12,Severity.WOUND,2,12,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	TachRifle(		"Tach Rifle", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.X, /*Hull*/12, /*Power*/8, Moneyz.money(30, "M"), /*Acc*/+1, new FireRange(6,8,10), DamageType.HIIMPACT, Firepower.MEDIUM,
					new DamageSet(1,12,Severity.WOUND,1,8,Severity.MORTAL,1,12,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	HeavyAccelerator("Heavy Accelerator", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.N, /*Hull*/18, /*Power*/14, Moneyz.money(40, "M"), /*Acc*/+3, new FireRange(4,8,10), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(2,12,Severity.WOUND,2,12,Severity.MORTAL,2,12,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	AntimatterGun	("Antimatter Gun", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.A, /*Hull*/25, /*Power*/18, Moneyz.money(80, "M"), /*Acc*/+4, new FireRange(4,8,12), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(3,18,Severity.WOUND,3,12,Severity.MORTAL,3,12,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	SuperTachRifle	("Super Tach Rifle", WeaponType.PROJECTILE, ProgressLevel.PL7, Tech.X, /*Hull*/40, /*Power*/30, Moneyz.money(120, "M"), /*Acc*/+4, new FireRange(8,10,14), DamageType.HIIMPACT, Firepower.SUPERHEAVY,
					new DamageSet(2,16,Severity.MORTAL,2,24,Severity.MORTAL,2,16,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	SliverGun(		"Sliver Gun", WeaponType.PROJECTILE, ProgressLevel.PL8, Tech.N, /*Hull*/4, /*Power*/2, Moneyz.money(250, "K"), /*Acc*/-2, new FireRange(1,2,4), DamageType.HIIMPACT, Firepower.SMALL,
					new DamageSet(2,7,Severity.WOUND,4,9,Severity.WOUND,3,6,Severity.MORTAL), new WeaponModes(true,false,true,true)),
	
	NeutroniumDriver("Neutronium Driver", WeaponType.PROJECTILE, ProgressLevel.PL8, Tech.S, /*Hull*/8, /*Power*/4, Moneyz.money(4, "M"), /*Acc*/0, new FireRange(2,4,8), DamageType.HIIMPACT, Firepower.LIGHT,
					new DamageSet(4,9,Severity.WOUND,2,7,Severity.MORTAL,1,6,Severity.CRITICAL), new WeaponModes(true,true,false,false)),
	
	BombProjector(	"Bomb Projector", WeaponType.PROJECTILE, ProgressLevel.PL8, Tech.T, /*Hull*/10, /*Power*/15, Moneyz.money(15, "M"), /*Acc*/-1, new FireRange(4,6,8), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,12,Severity.STUN,2,12,Severity.WOUND,2,12,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	BombSalvo(		"Bomb Salvo", WeaponType.PROJECTILE, ProgressLevel.PL8, Tech.T, /*Hull*/12, /*Power*/18, Moneyz.money(20, "M"), /*Acc*/-1, new FireRange(5,7,10), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,12,Severity.STUN,2,12,Severity.WOUND,2,12,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	KineticConverter("Kinetic Converter", WeaponType.PROJECTILE, ProgressLevel.PL8, Tech.X, /*Hull*/20, /*Power*/10, Moneyz.money(50, "M"), /*Acc*/+2, new FireRange(4,8,16), DamageType.LOWIMPACT, Firepower.HEAVY,
					new DamageSet(4,15,Severity.WOUND,4,15,Severity.MORTAL,2,13,Severity.CRITICAL), new WeaponModes(true,true,false,false)),

	TunnelerGun(	"Tunneling Driver", WeaponType.PROJECTILE, ProgressLevel.PL9, Tech.Q, /*Hull*/30, /*Power*/15, Moneyz.money(90, "M"), /*Acc*/+3, new FireRange(5,10,20), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(2,12,Severity.STUN,2,12,Severity.WOUND,2,12,Severity.MORTAL), new WeaponModes(true,true,false,false)),
	
	BlackHoleGun(	"Black Hole Gun", WeaponType.PROJECTILE, ProgressLevel.PL9, Tech.G, /*Hull*/75, /*Power*/40, Moneyz.money(200, "M"), /*Acc*/+1, new FireRange(6,12,24), DamageType.LOWIMPACT, Firepower.SUPERHEAVY,
					new DamageSet(2,18,Severity.MORTAL,4,24,Severity.WOUND,3,24,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	// Missiles
	
	SMPBombRack(	"SMP Bomb Rack", WeaponType.MISSILE, ProgressLevel.PL6, Tech.N, /*Hull*/1, /*Power*/0, Moneyz.money(70, "K"), /*Acc*/-3, new FireRange(0,0,0), DamageType.HIIMPACT, Firepower.LIGHT,
					new DamageSet(3,8,Severity.STUN,3,8,Severity.WOUND,5,10,Severity.WOUND), new WeaponModes(true,false,false,false)),
	
	FusionBombRack(	"Fusion Bomb Rack", WeaponType.MISSILE, ProgressLevel.PL6, Tech.N, /*Hull*/1, /*Power*/0, Moneyz.money(1, "M"), /*Acc*/-1, new FireRange(0,0,0), DamageType.ENERGY, Firepower.HEAVY,
					new DamageSet(2,12,Severity.WOUND,2,16,Severity.MORTAL,2,12,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	CHEMissileRack(	"CHE Missile Rack", WeaponType.MISSILE, ProgressLevel.PL6, Tech.S, /*Hull*/2, /*Power*/1, Moneyz.money(270, "K"), /*Acc*/0, new FireRange(0,0,0), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(2,7,Severity.STUN,2,7,Severity.WOUND,3,6,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	SMPMissileMount("SMP Missile Mount", WeaponType.MISSILE, ProgressLevel.PL6, Tech.S, /*Hull*/10, /*Power*/1, Moneyz.money(1, "M")+Moneyz.money(600, "K"), /*Acc*/-1, new FireRange(0,0,0), DamageType.HIIMPACT, Firepower.LIGHT,
					new DamageSet(3,14,Severity.STUN,3,14,Severity.WOUND,5,10,Severity.WOUND), new WeaponModes(true,false,false,false)),
	
	AAMissileMount(	"AA Missile Mount", WeaponType.MISSILE, ProgressLevel.PL6, Tech.S, /*Hull*/10, /*Power*/1, Moneyz.money(1, "M")+Moneyz.money(600, "K"), /*Acc*/-2, new FireRange(0,0,0), DamageType.HIIMPACT, Firepower.SMALL,
					new DamageSet(1,8,Severity.WOUND,1,6,Severity.MORTAL,2,8,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	PlasmaMissileRack("Plasma Missile Rack", WeaponType.MISSILE, ProgressLevel.PL7, Tech.F, /*Hull*/2, /*Power*/1, Moneyz.money(850, "K"), /*Acc*/-3, new FireRange(0,0,0), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(4,9,Severity.WOUND,4,11,Severity.WOUND,3,8,Severity.MORTAL), new WeaponModes(true,false,false,false)),//add Tech.G
	
	MatterMissileRack("Matter Missile Rack", WeaponType.MISSILE, ProgressLevel.PL7, Tech.A, /*Hull*/2, /*Power*/1, Moneyz.money(4, "M")+Moneyz.money(200, "K"), /*Acc*/+1, new FireRange(0,0,0), DamageType.ENERGY, Firepower.SUPERHEAVY,
					new DamageSet(3,18,Severity.MORTAL,2,12,Severity.CRITICAL,4,14,Severity.CRITICAL), new WeaponModes(true,false,false,false)),//add Tech.G
	
	PlasmaMissileArray("Plasma Missile Array", WeaponType.MISSILE, ProgressLevel.PL7, Tech.Z, /*Hull*/20, /*Power*/2, Moneyz.money(16, "M"), /*Acc*/-3, new FireRange(0,0,0), DamageType.ENERGY, Firepower.LIGHT,
					new DamageSet(4,9,Severity.WOUND,4,11,Severity.WOUND,3,14,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	MatterMissileArray("Matter Missile Array", WeaponType.MISSILE, ProgressLevel.PL7, Tech.Z, /*Hull*/20, /*Power*/2, Moneyz.money(16, "M"), /*Acc*/+1, new FireRange(0,0,0), DamageType.ENERGY, Firepower.SUPERHEAVY,
					new DamageSet(3,18,Severity.MORTAL,2,12,Severity.CRITICAL,4,14,Severity.CRITICAL), new WeaponModes(true,false,false,false)),
	
	MRBMINESArray(	"MRB Mines Array", WeaponType.MISSILE, ProgressLevel.PL7, Tech.Z, /*Hull*/20, /*Power*/2, Moneyz.money(16, "M"), /*Acc*/-3, new FireRange(0,0,0), DamageType.ENERGY, Firepower.MEDIUM,
					new DamageSet(3,9,Severity.STUN,2,8,Severity.WOUND,2,9,Severity.MORTAL), new WeaponModes(true,false,false,false)),
	
	NovArray(		"Nova Array", WeaponType.MISSILE, ProgressLevel.PL8, Tech.C, /*Hull*/10, /*Power*/2, Moneyz.money(23, "M")+Moneyz.money(800, "K"), /*Acc*/-5, new FireRange(0,0,0), DamageType.LOWIMPACT, Firepower.HEAVY,
					new DamageSet(2,16,Severity.WOUND,2,12,Severity.MORTAL,2,8,Severity.CRITICAL), new WeaponModes(true,false,false,false));//add Tech.G
	
	
			
	String name;
	WeaponType weaponType;
	ProgressLevel level;
	Tech tech;
	int hull, power, cost, accuracy;
	DamageType damageType;
	FireRange range;
	Firepower firepower;
	WeaponModes modes;
	DamageSet damageSet;
	

	WeaponList(String name, WeaponType weaponType, ProgressLevel progressLevel, Tech tech, int hull, int power, int cost, int accuracy,
			FireRange range, DamageType damageType, Firepower firepower, 
			DamageSet damageSet, WeaponModes modes){
		this.name = name;
		this.weaponType = weaponType;
		this.level = progressLevel;
		this.tech = tech;
		this.hull = hull;
		this.power = power;
		this.cost = cost;
		this.accuracy = accuracy;
		this.range = range;
		this.damageType = damageType;
		this.firepower = firepower;
		this.damageSet = damageSet;
		this.modes = modes;
	}
	
	public static ArrayList<MyShipObject> getListBeams() {
		ArrayList<MyShipObject> fullList = new ArrayList<>();
		fullList.add(new Weapon(Laser));
		fullList.add(new Weapon(IRLaser));
		fullList.add(new Weapon(XRayLaser));
		fullList.add(new Weapon(HeavyLaser));
		fullList.add(new Weapon(NeutronGun));
		fullList.add(new Weapon(FusionLaser));
		fullList.add(new Weapon(Graser));
		fullList.add(new Weapon(HeavyNeutronGun));
		fullList.add(new Weapon(HydrogenBore));
		fullList.add(new Weapon(PlasmaCannon));
		fullList.add(new Weapon(ParticleBeam));
		fullList.add(new Weapon(HeavyParticleBeam));
		fullList.add(new Weapon(HeavyPlasmaBeam));
		fullList.add(new Weapon(MatterBeam));
		fullList.add(new Weapon(FusionBeam));
		fullList.add(new Weapon(QuantumCannon));
		fullList.add(new Weapon(BosonGun));
		fullList.add(new Weapon(HeavyMatterBeam));
		fullList.add(new Weapon(FusionBore));
		fullList.add(new Weapon(MaserCannon));
		fullList.add(new Weapon(KineticLance));
		fullList.add(new Weapon(PulseMaser));
		fullList.add(new Weapon(EMCannon));
		fullList.add(new Weapon(DarkFusionGun));
		fullList.add(new Weapon(GatlingMaser));
		fullList.add(new Weapon(WeakForceGun));
		fullList.add(new Weapon(StrongForceGun));
		fullList.add(new Weapon(ZeroBore));
		fullList.add(new Weapon(Blacklaser));
		fullList.add(new Weapon(TachyonGun));
		fullList.add(new Weapon(StringProjector));
		
		return fullList;
	}
	
	public static ArrayList<MyShipObject> getListProjectiles() {
		ArrayList<MyShipObject> fullList = new ArrayList<>();
		fullList.add(new Weapon(PointDefense));
		fullList.add(new Weapon(RailCannon));
		fullList.add(new Weapon(NeedleDriver));
		fullList.add(new Weapon(GaussGun));
		fullList.add(new Weapon(HiRailGun));
		fullList.add(new Weapon(MassCannon));
		fullList.add(new Weapon(HeavyMassCannon));
		fullList.add(new Weapon(Accelerator));
		fullList.add(new Weapon(TachRifle));
		fullList.add(new Weapon(HeavyAccelerator));
		fullList.add(new Weapon(AntimatterGun	));
		fullList.add(new Weapon(SuperTachRifle	));
		fullList.add(new Weapon(SliverGun));
		fullList.add(new Weapon(NeutroniumDriver));
		fullList.add(new Weapon(BombProjector));
		fullList.add(new Weapon(BombSalvo));
		fullList.add(new Weapon(KineticConverter));
		fullList.add(new Weapon(TunnelerGun));
		fullList.add(new Weapon(BlackHoleGun));
		
		return fullList;
	}
	
	public static ArrayList<String> getListTitles(){
		
		ArrayList<String> listTitles = new ArrayList<String>();
		listTitles.add("Name");
		listTitles.add("Tech");
		listTitles.add("Hull");
		listTitles.add("POW");
		listTitles.add("Cost");
		listTitles.add("Acc");
		listTitles.add("Range");
		listTitles.add("Type");
		listTitles.add("Damage");
		listTitles.add("Mode");
		
		return listTitles;
	}

}
