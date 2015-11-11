package uk.ac.york.mondo.integration.server.users.servlet.servlets;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServlet;
import org.mapdb.BTreeMap;
import org.mapdb.DB;

import uk.ac.york.mondo.integration.api.UserExists;
import uk.ac.york.mondo.integration.api.UserNotFound;
import uk.ac.york.mondo.integration.api.UserProfile;
import uk.ac.york.mondo.integration.api.Users;
import uk.ac.york.mondo.integration.api.Users.Iface;
import uk.ac.york.mondo.integration.server.users.servlet.Activator;
import uk.ac.york.mondo.integration.server.users.servlet.db.User;
import uk.ac.york.mondo.integration.server.users.servlet.db.UserStorage;

public class UserServlet extends TServlet {
	private static final long serialVersionUID = 1L;

	private static class UsersIface implements Iface {

		@Override
		public void createUser(String username, String password, UserProfile profile) throws UserExists, TException {
			final UserStorage storage = Activator.getInstance().getStorage();
			final DB db = storage.getTxMaker().makeTx();
			try {
				BTreeMap<String, User> userMap = storage.getUserMap(db);
				if (userMap.containsKey(username)) {
					throw new UserExists();
				}

				final String hashed = UserStorage.getPasswordService().encryptPassword(password);
				final User user = User.builder()
						.username(username)
						.hashedPassword(hashed)
						.realName(profile.realName)
						.isAdmin(profile.admin)
						.build();
				userMap.put(username, user);
				db.commit();
			} finally {
				db.close();
			}
		}

		@Override
		public void updateProfile(String username, UserProfile profile) throws UserNotFound, TException {
			final UserStorage storage = Activator.getInstance().getStorage();
			final DB db = storage.getTxMaker().makeTx();
			try {
				final BTreeMap<String, User> userMap = storage.getUserMap(db);
				final User oldUser = userMap.get(username);
				if (oldUser == null) {
					throw new UserNotFound();
				}

				final User newUser = User.builder(oldUser)
						.realName(profile.realName)
						.isAdmin(profile.admin)
						.build();
				userMap.put(username, newUser);
				db.commit();
			} finally {
				db.close();
			}
		}

		@Override
		public void updatePassword(String username, String password) throws UserNotFound, TException {
			final UserStorage storage = Activator.getInstance().getStorage();
			final DB db = storage.getTxMaker().makeTx();
			try {
				final BTreeMap<String, User> userMap = storage.getUserMap(db);
				final User oldUser = userMap.get(username);
				if (oldUser == null) {
					throw new UserNotFound();
				}

				final String hashed = UserStorage.getPasswordService().encryptPassword(password);
				final User newUser = User.builder(oldUser).hashedPassword(hashed).build();
				userMap.put(username, newUser);
				db.commit();
			} finally {
				db.close();
			}
		}

		@Override
		public void deleteUser(String username) throws UserNotFound, TException {
			final UserStorage storage = Activator.getInstance().getStorage();
			final DB db = storage.getTxMaker().makeTx();
			try {
				final BTreeMap<String, User> userMap = storage.getUserMap(db);
				if (!userMap.containsKey(username)) {
					throw new UserNotFound();
				}
				userMap.remove(username);
				db.commit();
			} finally {
				db.close();
			}
		}
	}

	public UserServlet() throws Exception {
		super(new Users.Processor<Users.Iface>(new UsersIface()), new TJSONProtocol.Factory());
	}
}