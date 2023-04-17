import React, { useEffect, useState } from "react";
import { UserContext } from "./UserContext";
import { getCurrentUserData } from "../services/auth/auth_service";
const UserProvider = ({ children }) => {
  const [user, setUser] = useState({});

  useEffect(() => {
    setUser(getCurrentUserData());
  }, []);
  return (
    <UserContext.Provider value={{ user, setUser }}>
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;