import React, { useEffect, useState } from "react";
import { UserContext } from "./UserContext";
import { getCurrentUserData, isLoggedIn } from "../services/auth/auth_service";
import { getUserById } from "../services/UserService";
const UserProvider = ({ children }) => {
  const [currentUserData, setCurrentUserData] = useState({});
  const [user, setUser] = useState({});

  useEffect(() => {
    setCurrentUserData(getCurrentUserData())
    populateUser();
  }, []);

  const populateUser = async () => {
    try {
      if (isLoggedIn()) {
        const userId = currentUserData.userId;
        const userData = await getUserById(userId);

        setUser(userData.data);
      } else {
        return;
      }
    } catch {
      console.log("error in fetching user!");
    }
  };
  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;
