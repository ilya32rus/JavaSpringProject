import { useState } from 'react';
import './App.css';
import MainTable from './features/mainTable';
import CreatePersonForm from "./features/form/person";
import UpdatePersonForm from "./features/form/person_update";
import CreateRoomFrom from "./features/form/room";
import UpdateRoomForm from "./features/form/room_update";

let sectionSelect = [{ label: 'Клиенты' }, { label: 'Комнаты' }]

function App() {

  const [section, setSection] = useState('Клиенты');
  const [state, setState] = useState(['Список']);

  const openList = (e) => {
    setSection(e);
    setState(['Список']);
  }

  const openUpdate = (e) => setState(['Обновление', e]);

  return (
    <div className="App">
      <div className="selector_container">
        {
          sectionSelect.map(({ label }, index) => (
            <button className={`select_button ${section === label && 'active_select_button'}`} onClick={() => { setSection(label); setState(['Список']) }} key={index}>{label}</button>
          ))
        }
      </div>

      {
        state[0] === 'Список' && <div className="button_form"> <button onClick={() => setState(['Создание'])}>Создать</button> </div>
      }

      <div className="content_container">
        {
          section === 'Клиенты' && state[0] === 'Создание' && <CreatePersonForm close={() => openList('Клиенты')} />
        }
        {
          section === 'Клиенты' && state[0] === 'Список' && <MainTable head={['id', 'Имя', 'Паспортные данные']} body={['id', 'name', 'passportId']} endpoint={'person'} upd={openUpdate} />
        }
        {
          section === 'Клиенты' && state[0] === 'Обновление' && <UpdatePersonForm idf={state[1]} close={() => openList('Клиенты')} />
        }
        {
          section === 'Комнаты' && state[0] === 'Создание' && <CreateRoomFrom close={() => openList('Комнаты')} />
        }
        {
          section === 'Комнаты' && state[0] === 'Список' && <MainTable head={['id', 'Тип', 'Стиль', 'Этаж']} body={['id', 'type', 'style','level']} endpoint={'room'} upd={openUpdate} />
        }
        {
          section === 'Комнаты' && state[0] === 'Обновление' && <UpdateRoomForm idf={state[1]} close={() => openList('Комнаты')} />
        }

      </div>
    </div>
  );
}

export default App;
