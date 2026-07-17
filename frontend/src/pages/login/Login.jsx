import Header from '../../components/header/Header'
import Footer from '../../components/footer/Footer';

import './Login.css'
import { useEffect, useState } from 'react';


function LoginFormArea({setRegisterMode, userAlert}){

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loginAlert, setLoginAlert] = useState("");

  useEffect(() => {
    
  }, [loginAlert]);

  function clearInputs(){
    setEmail("");
    setPassword("");
  }

  async function loginUser(){

    const payload = {
      'email': email,
      'password': password
    }

    if(!payload.email || !payload.password){
      setLoginAlert("Preencha todos os campos...");
      return;
    }

    const res = await fetch(
      "http://192.168.1.102:8080/user/login",
      {
        method: 'POST',
        headers: {
          'content-type': 'application/json'
        },
        body: JSON.stringify(payload)
      }
    );

    if(!res.ok){
      const error = await res.json();

      setLoginAlert(error.message);
      throw new Error(error.message);
    }

    const response = await res.json();

    console.log("SUCESSO: ", response.message);
    clearInputs();
    setLoginAlert(response.message)
    window.alert(response.message);
  }

  return (
    <div className='loginContent'>
      
      <h2>email:</h2>
      <input className='inputsGeneral' type="text" onChange={(e) => {setEmail(e.target.value)}}/>
      <h2>Password:</h2>
      <input className='inputsGeneral' type="password" autoComplete='current-password' onChange={(e) => {setPassword(e.target.value)}}/>
      <button className='butGeneral' onClick={loginUser}>Sign In</button>
      <span className='registerSpan'>
        <span>Nao possui uma conta? </span><span className='signUpClick' onClick={setRegisterMode}>Sign Up</span>
      </span>
      <span className='alert'>{userAlert}</span>
      <span className='alert'>{loginAlert}</span>

    </div>
  );
}

function RegisterFormArea({setLoginMode, setUserAlert}){

  const [username, setName] = useState("");
  const [useremail, setEmail] = useState("");
  const [userpassword, setPassword] = useState("");
  const [registerAlert, setRegisterAlert] = useState("");

  useEffect(() => {
    
  }, [username, useremail, userpassword, registerAlert]);

  function clearInputs(){
    setName("");
    setEmail("");
    setPassword("");
  }


  async function registerUser(){


    try{
      const payload = {
        'name': username,
        'email': useremail,
        'password': userpassword
      }

      if(!payload.email || !payload.name || !payload.password){
        setRegisterAlert("Preencha todos os campos...");
        return;
      }

      const res = await fetch(
        "http://192.168.1.102:8080/user/register",
        {
          method: 'POST',
          headers: {
            'content-type': 'application/json'
          },
          body: JSON.stringify(payload)
        }
      );

      if(!res.ok){
        const error = await res.json();

        setRegisterAlert(error.message);
        throw new Error(error.message);
      }

      const response = await res.json();

      console.log("SUCESSO: ", response);
      clearInputs();
      setUserAlert("USUARIO CADASTRADO COM SUCESSO!");
      setLoginMode();
    } catch (err){
      console.error(err.message);
    }
    
  }

  return (
    <div className='loginContent'>
      <h2>name:</h2>
      <input className='inputsGeneral' type="text" onChange={(e) => {setName(e.target.value)}} required/>
      <h2>email:</h2>
      <input className='inputsGeneral' type="text" onChange={(e) => {setEmail(e.target.value)}} required/>
      <h2>Password:</h2>
      <input className='inputsGeneral' type="password" autoComplete='new-password' onChange={(e) => {setPassword(e.target.value)}} required/>
      <button className='butGeneral' onClick={registerUser}>Sign Up</button>

      <span className='registerSpan'>
        <span>Já possui uma conta? </span><span className='signUpClick' onClick={setLoginMode}>Sign In</span>
      </span>

      <span className='alert'>{registerAlert}</span>

    </div>
  );
}

function Login(){

  const [register, setRegister] = useState(false);
  const [userAlert, setUserAlert] = useState("");

  function alternateLoginRegister(){

    if(register) setRegister(false)
    else setRegister(true);

  }


  useEffect(() => {

  }, [register, userAlert])

  return(
    <div className='body'>
        <Header isLoginPage={true}/>

        {
          !register
          ?
            <LoginFormArea setRegisterMode={alternateLoginRegister} userAlert={userAlert}/>
          :
            <RegisterFormArea setLoginMode={alternateLoginRegister} setUserAlert={setUserAlert}/>
        }
        <Footer/>
    </div>
  );
}

export default Login;
