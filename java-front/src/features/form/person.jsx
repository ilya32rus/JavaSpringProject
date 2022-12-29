import { useMemo } from 'react';
import { useState } from 'react'
import { createData } from '../../actions/actions';
import Input from '../../components/input/input'
import './form.css'
import CheckBox from "../../components/input/checkbox";

export default function CreatePersonForm({ close }) {
    const [name, setName] = useState('');
    const [passport, setPassport] = useState('');

    const memoDisabled = useMemo(() => {
        return (!name || !passport);
    }, [name, passport])

    const submit = (e) => {
        e.preventDefault();
        createData('person', {
            name: name,
            passportId: passport
        })
    }

    return (
        <form className="container_form" onSubmit={submit}>
            <div className="button_form">
                <button onClick={close}>Закрыть</button>
                <button type="submit" className={memoDisabled ? 'disabled' : ''} disabled={memoDisabled} >Отправить</button>
            </div>
            <Input label='Имя' change={(e) => setName(e.value)} />
            <Input label='Паспорт' change={(e) => setPassport(e.value)} />
        </form>
    )
}