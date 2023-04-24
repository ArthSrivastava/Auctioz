import React, { useEffect, useState } from "react";
import { UserContext } from "./UserContext";
import { doLogout, getCurrentUserData, isLoggedIn } from "../services/auth/auth_service";
import { getUserById } from "../services/UserService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
const UserProvider = ({ children }) => {
  const [currentUserData, setCurrentUserData] = useState({});
  const [user, setUser] = useState({});
  const navigate = useNavigate();
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
    } catch (error) {
      if(error.response.status === 401) {
        toast.error("Please login again!")
        doLogout(() => navigate("/"));
      }
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
