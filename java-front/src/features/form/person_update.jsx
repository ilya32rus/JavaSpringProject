import { useMemo } from 'react';
import { useState } from 'react'
import { updateData } from '../../actions/actions';
import Input from '../../components/input/input'
import './form.css'
import CheckBox from "../../components/input/checkbox";

export default function UpdatePersonForm({ idf, close }) {
    const [id,] = useState(idf['id']);
    const [name, setName] = useState(idf['name']);
    const [passport, setPassport] = useState(idf['passportId']);

    const memoDisabled = useMemo(() => {
        return (!id);
    }, [id, name, passport])

    const submit = (e) => {
        e.preventDefault();
        updateData('person', {
            id: id,
            name: name,
            passportId: passport
        }, id)
    }

    return (
        <form className="container_form" onSubmit={submit}>
            <div className="button_form">
                <button type="button" onClick={close}>Закрыть</button>
                <button type="submit" className={memoDisabled ? 'disabled' : ''} disabled={memoDisabled} >Отправить</button>
            </div>
            <Input disabled={true} label='id' value={id} />
            <Input value={name} label='Имя' change={(e) => setName(e.value)} />
            <Input value={passport} label='Паспорт' change={(e) => setPassport(e.value)} />
        </form>
    )
}