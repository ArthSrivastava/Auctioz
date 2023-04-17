import React from 'react'
import UserProvider from '../contexts/UserProvider'
import CustomNavbar from './CustomNavbar'

const Base = ({ children }) => {
  return (
    <UserProvider>
        <CustomNavbar />
        { children }
    </UserProvider>
  )
}

export default Base