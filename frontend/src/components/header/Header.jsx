import {Link} from "react-router-dom"
import { useEffect, useState } from "react"
import reactLogo from '../../assets/react.svg'
import './Header.css'

function NotAuth({loginPage}){
    return(
        <div className='headerOption'>
            {
                !loginPage
                ?
                    <Link to="/login">
                        <button className='headerSignBut'>SignIn</button>
                    </Link>
                :
                    <span></span>
            }
        </div>
    );
}

function IsAuth({user}){
    return(
        <div className='headerUserName'>
            {user.name}
        </div>
    );
}



function Header({isLoginPage, userData}){

    const [loginPage, setLoginPage] = useState(false);

    useEffect(() => {
        const checkLoginPage = async () => {

            setLoginPage(isLoginPage);
        }



        checkLoginPage();

    },[]);

    console.log(userData);

    return(
        <header className={!loginPage ? 'headerNotLogin' : 'headerLogin'}>
            <img className='logozin' src={reactLogo} alt="" />
            <h1 className={!loginPage? 'h1NotLogin' : 'h1Login'}>Jairo Movies</h1>
            {
                userData == null
                ?
                    <NotAuth loginPage={loginPage}/>
                :
                    <IsAuth user={userData}/>
            }
      </header>
    );
}

export default Header;