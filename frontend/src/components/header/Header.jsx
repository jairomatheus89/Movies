import {Link} from "react-router-dom"
import { useEffect, useState } from "react"
import reactLogo from '../../assets/react.svg'
import './Header.css'

function Header({isLoginPage}){

    const [loginPage, setLoginPage] = useState(false);

    useEffect(() => {
        const checkLoginPage = async () => {
            setLoginPage(isLoginPage);
        }
        checkLoginPage();
    },[loginPage]);

    return(
        <header className={!loginPage ? 'headerNotLogin' : 'headerLogin'}>
            <img className='logozin' src={reactLogo} alt="" />
            <h1 className={!loginPage? 'h1NotLogin' : 'h1Login'}>Jairo Movies</h1>
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
      </header>
    );
}

export default Header;