import React, { useEffect, useState } from "react";
import { UserContext } from "./UserContext";
import { getCurrentUserData, isLoggedIn } from "../services/auth/auth_service";
import { getUserById } from "../services/UserService";
const UserProvider = ({ children }) => {
  const [currentUserData, setCurrentUserData] = useState({});
  const [user, setUser] = useState({});

  useEffect(() => {
    populateCurrentUserData();
  }, []);

  const populateCurrentUserData = async () => {
    if(!isLoggedIn()) {
      return;
    }
    const localStorageData = await getCurrentUserData();
    setCurrentUserData(localStorageData);
  };

  useEffect(() => {
    populateUser();
  }, [currentUserData]);
  const populateUser = async () => {
    if(currentUserData.userId === undefined) {
      return;
    }
    try {
        const userId = currentUserData.userId;
        const userData = await getUserById(userId);
        setUser(userData.data);
    } catch {
      console.log("error in fetching user!");
    }
  };
  return (
    <UserContext.Provider
      value={{
        user,
        setUser,
        currentUserData,
        setCurrentUserData,
      }}
    >
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;
