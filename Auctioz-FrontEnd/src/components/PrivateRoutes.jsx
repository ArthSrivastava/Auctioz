import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { isLoggedIn } from '../services/auth/auth_service'

const PrivateRoutes = () => {
  return isLoggedIn() ? <Outlet /> : <Navigate to="/login" />
}

export default PrivateRoutes