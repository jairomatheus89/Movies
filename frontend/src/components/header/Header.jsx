import { useNavigate, Link } from "react-router-dom"
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

function IsAuth({user, isFavPage}){

    const navigate = useNavigate();

    return(
        <div className='headerUserName'>
            {user.name}
            <div className="favBut">
                {
                    !isFavPage
                    ?
                        <span onClick={() => navigate("/perfil/favorites")}>Favoritos</span>
                    :
                        <span onClick={() => navigate("/perfil")}>Buscar</span>
                }
            </div>
        </div>
        
    );
}



function Header({isLoginPage, userData, isFavPage}){

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
                    <IsAuth user={userData} isFavPage={isFavPage}/>
            }
      </header>
    );
}

export default Header;