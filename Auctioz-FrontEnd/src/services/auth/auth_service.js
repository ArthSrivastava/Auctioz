
//login
export const doLogin = (data, next) => {
    localStorage.setItem("data", JSON.stringify(data))
    next()
}

//logout
export const doLogout = (next) => {
    localStorage.removeItem("data")
    next()
}

//get user data
export const getCurrentUserData = () => {
    return isLoggedIn() ? JSON.parse(localStorage.getItem("data")) : undefined 
}

//check if logged in
export const isLoggedIn = () => {
    let data = localStorage.getItem("data")
    return data != undefined ? true : false
}

//get jwt token
export const getToken = () => {
    if(isLoggedIn()) {
        let token = JSON.parse(localStorage.getItem("data")).accessToken
        return token
    } else {
        return null
    }
}