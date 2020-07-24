package me.joseph.skyblock.managers;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.mapping.MapperOptions;
import me.joseph.skyblock.Island;
import me.joseph.skyblock.IslandPlayer;
import me.joseph.skyblock.Skyblock;

public class MongoManager {

	final static Morphia morphia = new Morphia().map(IslandPlayer.class, Island.class);
	private static final Datastore datastore = morphia.createDatastore(new MongoClient(Skyblock.getInstance().getConfig().getString("mongo.ip"), Skyblock.getInstance().getConfig().getInt("mongo.port")), Skyblock.getInstance().getConfig().getString("mongo.dbname"));
	static {
		MapperOptions o = morphia.getMapper().getOptions();
		o.setStoreNulls(true);
		o.setStoreEmpties(true);
	}

	public static Datastore getDatastore() {
		datastore.ensureIndexes();
		return datastore;
	}
}
